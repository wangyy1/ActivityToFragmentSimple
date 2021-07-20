package com.wyy.activitytofragmentsimple

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wyy.activitytofragmentsimple.databinding.FragmentHomeMyBinding
import java.util.*

/**
 *
 * @author wangyongyong
 * @since
 */
class MyHomeFragment : Fragment() {

    private var _binding : FragmentHomeMyBinding? = null

    private val binding get() = _binding!!

    private lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeActivity) {
            homeActivity = context;
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeMyBinding.inflate(inflater, container, false)
        binding.btnMain.setOnClickListener {
            homeActivity.addFragment(SecondFragment::class.java)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}