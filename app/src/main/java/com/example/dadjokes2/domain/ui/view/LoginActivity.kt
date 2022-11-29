package com.example.dadjokes2.domain.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.dadjokes2.MainActivity
import com.example.dadjokes2.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    data class User(val email: String, val uid: String, val qty: Long)

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var loginButton: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // onCreate
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        firebaseAuth = FirebaseAuth.getInstance()

        loginButton = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, 100)
        }
        checkUser()
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //firebaseAuth.signOut()

            Log.d("LOGIN-ACTIVITY", "CheckUser")

            val firebaseUser = firebaseAuth.currentUser
            val uid = firebaseUser!!.uid
            val email : String = firebaseUser.email!!

            db.collection("users").document(email).get().addOnSuccessListener {
                var qty = it.get("qty") as Long
                qty++
                db.collection("users").document(email).set(
                    hashMapOf(
                        "email" to email,
                        "uid" to uid,
                        "qty" to qty
                    )
                )
            }


            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }
            catch (e: Exception) {
                Log.d("LOGIN-ACTIVITY", "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                val firebaseUser = firebaseAuth.currentUser
                val uid = firebaseUser!!.uid
                val email : String = firebaseUser.email!!

                if (authResult.additionalUserInfo!!.isNewUser) {
                    Log.d("LOGIN-ACTIVITY", "NewUser")
                    db.collection("users").document(email).set(
                        hashMapOf(
                            "email" to email,
                            "uid" to uid,
                            "qty" to 1
                        )
                    )

                    // Crear Account
                    Toast.makeText(this@LoginActivity, "Cuenta creada...", Toast.LENGTH_LONG).show()
                }
                else {
                    Log.d("LOGIN-ACTIVITY", "RecurringUser")

                    db.collection("users").document(email).get().addOnSuccessListener {
                        var qty = it.get("qty") as Int
                        qty++
                        db.collection("users").document(email).set(
                            hashMapOf(
                                "email" to email,
                                "uid" to uid,
                                "qty" to qty
                            )
                        )
                    }

                    Toast.makeText(this@LoginActivity, "Cuenta existente...", Toast.LENGTH_LONG).show()
                }
                Log.d("LOGIN-ACTIVITY", "Start Activity Main")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener { e->
                Toast.makeText(this@LoginActivity, "Login fallido...", Toast.LENGTH_LONG).show()
            }
    }
}