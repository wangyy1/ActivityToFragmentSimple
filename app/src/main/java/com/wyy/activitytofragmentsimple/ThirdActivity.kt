package com.wyy.activitytofragmentsimple

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wyy.activitytofragmentsimple.databinding.ActivityFirstBinding
import com.wyy.activitytofragmentsimple.databinding.ActivityThirdBinding

/**
 *
 * @author wangyongyong
 * @since
 */
class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMain.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}