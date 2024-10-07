package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraimc.databinding.ActivityExportBinding

class ExportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExportBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val imcResult = intent.getStringExtra("IMC_RESULT")
        binding.exportTextView.text = imcResult ?: "Nenhum dado recebido"

        binding.buttonExport.setOnClickListener {
            exportData(imcResult ?: "Nenhum dado disponível")
        }
    }

    private fun exportData(data: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, data)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Exportar IMC"))
    }
}
