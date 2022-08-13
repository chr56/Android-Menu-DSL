/*
 *
 * Copyright (C) 2022 chr_56
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package menu.dsl.test

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem.SHOW_AS_ACTION_NEVER
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.github.chr56.android.menu_dsl.attach
import com.github.chr56.android.menu_dsl.menuItem
import com.github.chr56.android.menu_dsl.submenu
import menu.dsl.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        injectMenu(menu)
        return true
    }
    private fun injectMenu(menu: Menu) {
        attach(menu) {
            menuItem {
                itemId = R.id.setting
                title = getString(R.string.title_setting)
                icon =
                    AppCompatResources.getDrawable(this@MainActivity, android.R.drawable.ic_menu_preferences)
                showAsActionFlag = SHOW_AS_ACTION_NEVER
                onClick {
                    Toast.makeText(this@MainActivity, "Setting", Toast.LENGTH_SHORT).show()
                    true
                }
            }
            menuItem {
                itemId = R.id.about
                titleRes(R.string.title_about)
                icon =
                    AppCompatResources.getDrawable(this@MainActivity, android.R.drawable.ic_menu_info_details)
                showAsActionFlag = SHOW_AS_ACTION_NEVER
            }
            submenu("More") {
                menuItem("Test 1") {}
                menuItem {
                    title = "Test 2"
                }
            }
        }
    }
}
