/*
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
 */

package com.github.chr56.android.menu_dsl

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.ActionProvider
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

@Suppress("NOTHING_TO_INLINE")
open class MenuItemCfg {

    var itemId: Int = Menu.NONE
    var groupId: Int = Menu.NONE
    var order: Int = Menu.NONE
    var title: CharSequence? = null
    var titleCondensed: CharSequence? = null
    var icon: Drawable? = null
    var showAsActionFlag: Int = SHOW_AS_ACTION_IF_ROOM

    var tooltipText: CharSequence? = null
    var contentDescription: CharSequence? = null

    var enabled: Boolean = true
    var visible: Boolean = true
    var checkable: Boolean = false
    var checked: Boolean = false

    var menuItemClickListener: MenuItem.OnMenuItemClickListener? = null

    inline fun onClick(noinline callback: (MenuItem) -> Boolean) {
        menuItemClickListener = MenuItem.OnMenuItemClickListener(callback)
    }

    var intent: Intent? = null

    var numericShortcut: Char? = null
    var alphabeticShortcut: Char? = null

    var customActionView: View? = null
    var actionProvider: ActionProvider? = null

    fun titleRes(@StringRes id: Int, context: Context) {
        title = context.getString(id)
    }
    fun iconRes(@DrawableRes id: Int, context: Context) {
        icon = AppCompatResources.getDrawable(context, id)
    }

    // TODO: SubMenu

    companion object {
        const val SHOW_AS_ACTION_NEVER = MenuItem.SHOW_AS_ACTION_NEVER
        const val SHOW_AS_ACTION_IF_ROOM = MenuItem.SHOW_AS_ACTION_IF_ROOM
        const val SHOW_AS_ACTION_ALWAYS = MenuItem.SHOW_AS_ACTION_ALWAYS
        const val SHOW_AS_ACTION_WITH_TEXT = MenuItem.SHOW_AS_ACTION_WITH_TEXT
        const val SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
    }
}
