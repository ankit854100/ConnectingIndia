package com.anand.connectingindia

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.concurrent.TimeUnit

class SignUpActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var codeSent : String
    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("please wait")

        generateOtp.setOnClickListener {
            if (phoneNumberEditText.text.toString().equals("")){
                Toast.makeText(this@SignUpActivity, "Phone number cannot be blank", Toast.LENGTH_SHORT).show()
            }
            else if(phoneNumberEditText.text.toString().length < 10){
                Toast.makeText(this@SignUpActivity, "Phone number must have 10 digits", Toast.LENGTH_SHORT).show()
            }
            else if(phoneNumberEditText.text.toString().length == 10){

                phoneNumberEditText.isEnabled = false
                generateOtp.isEnabled = false
                otpEnteredEditText.visibility = View.VISIBLE
                confirmOtp.visibility = View.VISIBLE
                sendVerificationCode()
                progressDialog.show()
            }
            else{
                Toast.makeText(this@SignUpActivity, "PLease enter mobile number correctly", Toast.LENGTH_SHORT).show()

            }


        }

        confirmOtp.setOnClickListener {
            if(otpEnteredEditText.text.toString().equals("")){
                Toast.makeText(this@SignUpActivity, "otp cannot be blank", Toast.LENGTH_SHORT).show()
            }
            else if(otpEnteredEditText.text.toString().length < 6){
                Toast.makeText(this@SignUpActivity, "otp must be of 6 digits", Toast.LENGTH_SHORT).show()

            }
            else if(otpEnteredEditText.text.toString().length == 6){

                    var code = otpEnteredEditText.text.toString()
                    progressDialog.show()
                    confirmOtp.isEnabled = false
                    otpEnteredEditText.isEnabled = false
                    var credential : PhoneAuthCredential = PhoneAuthProvider.getCredential(codeSent, code)
                    signInWithAuthCredentials(credential)
            }
            else{
                Toast.makeText(this@SignUpActivity, "Enter otp correctly", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun signInWithAuthCredentials(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@SignUpActivity, "Login successful", Toast.LENGTH_LONG ).show()
                    var intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    progressDialog.dismiss()

                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this@SignUpActivity, task.toString(), Toast.LENGTH_LONG ).show()
                    }
                }
            }

    }

    private fun verificationCallbacks(){

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {

            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                codeSent = verificationId
                progressDialog.dismiss()
            }
        }
    }

    private fun sendVerificationCode() {
        verificationCallbacks()

        var phoneNumber = "+91" + phoneNumberEditText.text.toString()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            callbacks)
    }

    override fun onStart() {
        super.onStart()

        if(auth.currentUser != null){
            var intent = Intent(this@SignUpActivity, HomeActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

    }



}
