package com.example.aboutfilms
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ImageView as ImageView1

private const val KEY_CUSTOM_THEME = "KEY_CUSTOM_THEME"

class MainActivity : AppCompatActivity() {
    private var selectedFilm: Int = 0
    var selectedTheme = false


    private val preference by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        selectedTheme = preference.getBoolean(
            KEY_CUSTOM_THEME,
            true
        )
        if (selectedTheme) {
            setTheme(R.style.BlackTheme)
        } else {
            setTheme(R.style.DayTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textFilm1.text ="Терминатор"
        val imageView1 = findViewById<ImageView1>(R.id.imageView1)
        val imgResIdFilm1 = R.drawable.film1
        imageView1.setImageResource(imgResIdFilm1)

        textFilm2.text ="Рокки 2"
        val imageView2 = findViewById<ImageView1>(R.id.imageView2)
        val imgResIdFilm2 = R.drawable.film2
        imageView2.setImageResource(imgResIdFilm2)

        textFilm3.text ="нью-йорк"
        val imageView3 = findViewById<ImageView1>(R.id.imageView3)
        val imgResIdFilm3 = R.drawable.film3
        imageView3.setImageResource(imgResIdFilm3)

        btnShowAboutFilm1.setOnClickListener{
            selectedFilm = 1
            SelectNameFilm(selectedFilm)
            Log.i("happy", "btnShowAboutFilm1.setOnClickListener")
            // Создаем объект Intent для вызова новой Activity
            val intent =  Intent(this,DisplayAboutThisFilm::class.java)
            intent.putExtra("IndexPic", 1);
            intent.putExtra("Name", "Терминатор");
            intent.putExtra("Genre", "жанр: боевик");
            intent.putExtra("Content", "В центре сюжета — противостояние солдата и робота-терминатора, прибывших в 1984 год из постапокалиптического 2029 года. Цель терминатора: убить Сару Коннор — девушку, чей ещё нерождённый сын в возможном будущем выиграет войну человечества с машинами. Влюблённый в Сару солдат Кайл Риз пытается помешать терминатору. В фильме поднимаются проблемы путешествий во времени, судьбы, создания искусственного интеллекта, поведения людей в экстремальных ситуациях.");
            // start your next activity
            startActivityForResult(intent, 0)
        }
        btnShowAboutFilm2.setOnClickListener(){
            selectedFilm = 2
            SelectNameFilm(selectedFilm)
            Log.i("happy", "btnShowAboutFilm2.setOnClickListener")
            // Создаем объект Intent для вызова новой Activity
            val intent =  Intent(this,DisplayAboutThisFilm::class.java)
            intent.putExtra("IndexPic", 2);
            intent.putExtra("Name", "Рокки 2");
            intent.putExtra("Genre", "жанр: драма");
            intent.putExtra("Content", "Фильм (действие охватывает время с января по ноябрь 1976) начинается с 15-го раунда тяжёлого боя, показанного в первом фильме. Бой длился 15 раундов, оба боксёра получили значительные травмы. После боя Рокки зовёт Адриану, которая поднимается к рингу. Аполло Крид объявлен победителем боя решением судейства, Адриана и Рокки обнимаются, и Рокки говорит ей, что он её любит.");
            // start your next activity
            startActivityForResult(intent, 0)
        }
        btnShowAboutFilm3.setOnClickListener(){
            selectedFilm = 3
            SelectNameFilm(selectedFilm)
            Log.i("happy", "btnShowAboutFilm2.setOnClickListener")
            // Создаем объект Intent для вызова новой Activity
            val intent =  Intent(this,DisplayAboutThisFilm::class.java)
            intent.putExtra("IndexPic", 3);
            intent.putExtra("Name", "Нью-Йорк");
            intent.putExtra("Genre", "жанр: драма");
            intent.putExtra("Content", "Нью-Йорк, 1945 год. На концерте джаз-оркестра в ночном клубе вернувшийся с фронта Джимми (Де Ниро) настойчиво, но безуспешно пытается познакомиться с девушкой по имени Франсин (Миннелли). На следующее утро они снова сталкиваются в холле гостиницы, где она разыскивает подругу, а он добивается очередной отсрочки платежа за проживание. ");
            // start your next activity
            startActivityForResult(intent, 0)
        }
        btnSelectTheme.setOnClickListener(){

            if (selectedTheme==false) { selectedTheme=true }
                else { selectedTheme=false }
            Log.i("happy", "selectedTheme" + selectedTheme)
            preference.edit()
                .putBoolean(KEY_CUSTOM_THEME, selectedTheme)
                .apply()
            recreate()
        }
    }
    fun SelectNameFilm(curSelect: Int) {
        textFilm1.setTextColor(0xff000000.toInt())
        textFilm2.setTextColor(0xff000000.toInt())
        textFilm3.setTextColor(0xff000000.toInt())
        when (curSelect)
        {
            1 -> textFilm1.setTextColor(0xffFF0000.toInt())
            2 -> textFilm2.setTextColor(0xffFF0000.toInt())
            3 -> textFilm3.setTextColor(0xffFF0000.toInt())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == Activity.RESULT_OK){
                val rezCheckBox = data?.getIntExtra("CheckBox", 0 )
                Log.i("happy", "Activity.RESULT_OK CheckBox=" + rezCheckBox)
                val rezTextComment = data?.getStringExtra("TextComment")
                Log.i("happy", "Activity.RESULT_OK TextComment=" + rezTextComment)
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MESS_KEY, selectedFilm)

        Log.d("happy", "onSaveInstanceState selectedFilm:[$selectedFilm]")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedFilm = savedInstanceState.getInt(MESS_KEY,0)

        SelectNameFilm(selectedFilm);
        Log.d("happy", "onRestoreInstanceState selectedFilm:[$selectedFilm]")
    }

    companion object {
        const val MESS_KEY = "saved_mess_key"

    }
    override fun onBackPressed() {
        val dialog: Dialog = CustomDialog(this)
        dialog.setOnShowListener {
            //DO something
            Log.d("happy", "onShow")
        }
        dialog.setOnDismissListener {
            //DO something
            Log.d("happy", "onDismiss")
        }
        dialog.setOnCancelListener {
            //DO something
            Log.d("happy", "onCancel")
        }
        dialog.show()
    }
}
