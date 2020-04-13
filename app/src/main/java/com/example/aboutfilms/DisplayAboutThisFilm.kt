package com.example.aboutfilms

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ImageSpan
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.toSpannable
import androidx.core.text.toSpanned
import kotlinx.android.synthetic.main.activity_display_about_this_film.*

class DisplayAboutThisFilm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_about_this_film)

        val intent = getIntent()
        val IndexPic = intent.getIntExtra("IndexPic",0)
        val Name = intent.getStringExtra("Name")
        val Genre = intent.getStringExtra("Genre")
        val Content = intent.getStringExtra("Content")

        textName.text = Name
        textGanre.text=Genre
        textContent.text = Content
        var imageResourceId = R.drawable.film0

        when (IndexPic){
            1 -> imageResourceId = R.drawable.film1
            2 -> imageResourceId = R.drawable.film2
            3 -> imageResourceId = R.drawable.film3
        }
        imageView.setImageResource(imageResourceId)

        btnAboutClose.setOnClickListener(){
            val returnIntent = this.intent
            returnIntent.putExtra("CheckBox",if (checkBoxFilm.isChecked) 1 else 0)
            returnIntent.putExtra("TextComment",editText2.text.toString())
            setResult(Activity.RESULT_OK,returnIntent)
            this.finish();
        }

        btnAboutShare.setOnClickListener(){
            // Создаем  текстовое сообщение
            val textMessage = "Our message"
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
            sendIntent.type = "text/plain"
            val title = resources.getString(R.string.chooser_title)
            // Создаем Intent для отображения диалога выбора.
            val chooser = Intent.createChooser(sendIntent, title)
            // Проверяем, что intent может быть успешно обработан
            sendIntent.resolveActivity(packageManager)?.let {
                startActivity(chooser)
            }
        }
    }

}
