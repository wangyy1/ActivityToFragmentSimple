package com.wyy.activitytofragmentsimple

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyy.activitytofragmentsimple.databinding.FragmentMainMyBinding
import com.wyy.activitytofragmentsimple.databinding.FragmentSecondBinding
import com.wyy.activitytofragmentsimple.ui.home.HomeFragment
import java.util.*

/**
 *
 * @author wangyongyong
 * @since
 */
class SecondFragment : Fragment() {

    private var _binding : FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private var myMainActivity: MyMainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MyMainActivity) {
            myMainActivity = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding.toolbar.apply {
            setNavigationOnClickListener {
                myMainActivity?.popFragment()
            }
        }
        binding.btnMain.setOnClickListener {
            myMainActivity?.addFragment(SecondFragment::class.java)
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}