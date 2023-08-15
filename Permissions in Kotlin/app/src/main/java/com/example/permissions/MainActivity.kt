package com.example.permissions

import android.Manifest
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.permissions.ui.theme.PermissionsTheme

class MainActivity : ComponentActivity() {

    private var cameraResultLauncher : ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
                isGranted -> if (isGranted){
                    Toast.makeText(this, "Permission granted for camera.", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Pwemission denied for camera.", Toast.LENGTH_SHORT).show()

            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCameraPermission : Button = findViewById(R.id.btnCameraPermission)
        btnCameraPermission.setOnClickListener {
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M &&
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                showRationaleDialog("Permission Demo requires camera access",
                "Camera cannot be used because Camera access is denied")
            }else{
                cameraResultLauncher.launch(Manifest.permission.CAMERA)
            }
        }

    }

    private fun showRationaleDialog(
        title: String,
        message: String,
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                // Positive button click listener
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                // Negative button click listener
                dialog.dismiss()
            }
        builder.create().show()
    }

}