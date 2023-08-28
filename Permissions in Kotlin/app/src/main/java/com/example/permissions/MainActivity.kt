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

    //permission for the camera
    private var cameraResultLauncher : ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
                isGranted -> if (isGranted){
                    Toast.makeText(this, "Permission granted for camera.", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Pwemission denied for camera.", Toast.LENGTH_SHORT).show()
            }
        }


    //permission for camera, location and coarse location ---3options
    private var cameraAndLocationResultLauncher : ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()){
            permissions -> permissions.entries.forEach{
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted){
                    if (permissionName == Manifest.permission.ACCESS_FINE_LOCATION){
                        Toast.makeText(this, "Permission granted for fine location", Toast.LENGTH_SHORT).show()
                    }else if (permissionName == Manifest.permission.ACCESS_COARSE_LOCATION){
                        Toast.makeText(this, "Permission granted for COARSE Location", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Permission granted for Camera", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if (permissionName == Manifest.permission.ACCESS_FINE_LOCATION){
                        Toast.makeText(this, "Permission denied for fine location", Toast.LENGTH_SHORT).show()
                    }else if (permissionName == Manifest.permission.ACCESS_COARSE_LOCATION){
                        Toast.makeText(this, "Permission denied for COARSE Location", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Permission denied for Camera", Toast.LENGTH_SHORT).show()
                    }
                }
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
                cameraAndLocationResultLauncher.launch(
                    arrayOf(Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                )
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