package com.osvin.foodapp.trash
//
//
//import android.content.Intent
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.google.android.material.navigation.NavigationBarView
//import com.osvin.foodapp.R
//import com.osvin.foodapp.presentation.activity.*
//
//abstract class BaseActivity(private val navNumber: Int?): AppCompatActivity() {
//    companion object {
//        const val TAG = "BaseActivity"
//    }
//
//    private val bottomNavigationMenu: BottomNavigationView = findViewById(R.id.bottom_home_menu)
//    fun setUpBottomNavigationView(){
//        bottomNavigationMenu.apply {
//            labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_UNLABELED
//            itemIconSize = 29
//            //почитать документацию и отключить анимацию. добавить селекторы для всех иконок когда Алена выгрузит иконки
//        }
//
//        bottomNavigationMenu.setOnNavigationItemReselectedListener { item ->
//            var nextActivity =
//
//                when(item.itemId){
//                    R.id.home -> {
//                        Log.d(TAG, "item_home")
//                        ContainerMainActivity::class.java
//                    }
//                    R.id.search -> {
//                        Log.d(TAG, "item_search")
//                        SearchActivity::class.java
//                    }
//                    R.id.basket-> {
//                        Log.d(TAG, "item_add_res")
//                        BasketActivity::class.java
//                    }
//                    R.id.likes -> {
//                        Log.d(TAG, "item_likes")
//                        LikesActivity::class.java
//                    }
//                    R.id.user-> {
//                        Log.d(TAG, "item_user")
//                        UserActivity::class.java
//                    }
//                    else -> {
//                        Log.d(TAG, "Unknown item $item")
//                        null
//                    }
//                }
//            if(nextActivity != null){
//                val intent = Intent(this,nextActivity)
//                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION // нет анимации при запуске интента
//                overridePendingTransition(0,0) // нет анимации
//                startActivity(intent)
//                true
//            }else{
//                nextActivity = ContainerMainActivity::class.java
//                val intent = Intent(this,nextActivity)
//                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION // нет анимации при запуске интента
//                overridePendingTransition(0,0) // нет анимации
//                startActivity(intent)
//            }
//        }
//        bottomNavigationMenu.menu.getItem(navNumber!!).isChecked  = true


    //}

//}