package com.wyy.activitytofragmentsimple

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.wyy.activitytofragmentsimple.databinding.ActivityMainMyBinding

class MyMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMyBinding

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    fun popFragment (): Unit {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        }
    }

    fun addFragment(aClass: Class<out Fragment>) {
        addFragment(fragmentManager, aClass, R.id.fragment_container_view, null)
    }

    /**
     * Fragment的添加
     * @param manager Fragment管理器
     * @param aClass 相应的Fragment对象的getClass
     * @param containerId 容器的id
     * @param args 需要传值的话可将bundle填入  不需要传值就填null
     */
    private fun addFragment(
        manager: FragmentManager,
        aClass: Class<out Fragment?>,
        containerId: Int,
        args: Bundle?
    ) {
        val tag = aClass.name
        var fragment: Fragment? = manager.findFragmentByTag(tag)
        val transaction: FragmentTransaction = manager.beginTransaction() // 开启一个事务
        if (fragment == null) { // 没有添加
            try {
                fragment = aClass.newInstance() // 通过反射 new 出一个 fragment 的实例

                // 设置 fragment 进入，退出， 弹进，弹出的动画
                transaction.setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.pop_slide_out,
                    R.anim.pop_slide_in,
                    R.anim.slide_out
                )

//                transaction.setCustomAnimations(
//                    R.anim.slide_in,
//                    R.anim.fade_out,
//                    R.anim.fade_in,
//                    R.anim.slide_out
//                )
                transaction.add(containerId, fragment!!, tag)
                transaction.addToBackStack(tag) // 加入回退栈时制定一个tag，以便在找到指定的事务
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            if (fragment.isAdded) {
                if (fragment.isHidden) {
                    transaction.show(fragment)
                }
            } else {
                transaction.add(containerId, fragment, tag)
            }
        }
        if (fragment != null) {
            fragment.arguments = args
            hideBeforeFragment(manager, transaction, fragment)
            transaction.commit()
        }
    }


    /**
     * 除当前 fragment 以外的所有 fragment 进行隐藏
     *
     * @param manager
     * @param transaction
     * @param currentFragment
     */
    private fun hideBeforeFragment(
        manager: FragmentManager,
        transaction: FragmentTransaction,
        currentFragment: Fragment
    ) {
        val fragments: List<Fragment> = manager.fragments
        for (fragment in fragments) {
            if (fragment !== currentFragment && !fragment.isHidden) {
                transaction.hide(fragment)
            }
        }
    }

}