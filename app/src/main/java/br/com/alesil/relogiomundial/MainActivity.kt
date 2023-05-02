package br.com.alesil.relogiomundial

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import br.com.alesil.relogiomundial.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Definir a flag fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        ativarModoFullScreen()

        // Inicia a atualização da hora
        handler.post(updateTimeRunnable)
    }

    override fun onResume() {
        super.onResume()
        ativarModoFullScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove a atualização da hora quando a Activity for destruída
        handler.removeCallbacks(updateTimeRunnable)
    }

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            formatarPainel1("São Paulo", "America/Sao_Paulo")
            formatarPainel2("Pacific Standard Time (PST)", "America/Los_Angeles")
            formatarPainel3("UTC", "UTC")

            // Repete a atualização a cada 100 milisegundos
            handler.postDelayed(this, 100)
        }
    }

    private fun formatarPainel1(tmzName: String = "UTC", tmzId: String = "UTC") {
        // Obtenha o fuso horário desejado
        val selectedTimeZone = TimeZone.getTimeZone(tmzId)

        val currentTime = Calendar.getInstance(selectedTimeZone)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).apply { timeZone = selectedTimeZone }
        val dateFormat = SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault()).apply { timeZone = selectedTimeZone }
        val secondsFormat = SimpleDateFormat("ss", Locale.getDefault()).apply { timeZone = selectedTimeZone }

        val timeText = timeFormat.format(currentTime.time)
        val dateText = dateFormat.format(currentTime.time)
        val secondsText = secondsFormat.format(currentTime.time)

        binding.txtPainel1Hora.text = timeText
        binding.txtPainel1Localidade.text = tmzName
        binding.txtPainel1Data.text = dateText
        binding.txtPainel1Segundos.text = secondsText
    }

    private fun formatarPainel2(tmzName: String = "UTC", tmzId: String = "UTC") {
        // Obtenha o fuso horário desejado
        val selectedTimeZone = TimeZone.getTimeZone(tmzId)

        val currentTime = Calendar.getInstance(selectedTimeZone)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).apply { timeZone = selectedTimeZone }
        val dateFormat = SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault()).apply { timeZone = selectedTimeZone }
        val secondsFormat = SimpleDateFormat("ss", Locale.getDefault()).apply { timeZone = selectedTimeZone }

        val timeText = timeFormat.format(currentTime.time)
        val dateText = dateFormat.format(currentTime.time)
        val secondsText = secondsFormat.format(currentTime.time)

        binding.txtPainel2Hora.text = timeText
        binding.txtPainel2Localidade.text = tmzName
        binding.txtPainel2Data.text = dateText
        binding.txtPainel2Segundos.text = secondsText
    }

    private fun formatarPainel3(tmzName: String = "UTC", tmzId: String = "UTC") {
        // Obtenha o fuso horário desejado
        val selectedTimeZone = TimeZone.getTimeZone(tmzId)

        val currentTime = Calendar.getInstance(selectedTimeZone)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).apply { timeZone = selectedTimeZone }
        val dateFormat = SimpleDateFormat("EEE, dd/MM/yyyy", Locale.getDefault()).apply { timeZone = selectedTimeZone }
        val secondsFormat = SimpleDateFormat("ss", Locale.getDefault()).apply { timeZone = selectedTimeZone }

        val timeText = timeFormat.format(currentTime.time)
        val dateText = dateFormat.format(currentTime.time)
        val secondsText = secondsFormat.format(currentTime.time)

        binding.txtPainel3Hora.text = timeText
        binding.txtPainel3Localidade.text = tmzName
        binding.txtPainel3Data.text = dateText
        binding.txtPainel3Segundos.text = secondsText
    }

    private fun ativarModoFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            controller?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Código anterior para dispositivos com versões inferiores do Android
            val decorView = window.decorView
            @Suppress("DEPRECATION")
            decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }

    }
}