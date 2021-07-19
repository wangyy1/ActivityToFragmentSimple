package com.wyy.activitytofragmentsimple

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyy.activitytofragmentsimple.databinding.FragmentMainMyBinding
import java.util.*

/**
 *
 * @author wangyongyong
 * @since
 */
class MyMainFragment : Fragment() {

    private var _binding : FragmentMainMyBinding? = null

    private val binding get() = _binding!!

    private lateinit var myMainActivity: MyMainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MyMainActivity) {
            myMainActivity = context;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMyBinding.inflate(inflater, container, false)
        binding.btnMain.setOnClickListener {
            myMainActivity.addFragment(SecondFragment::class.java)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}