/*
 * Copyright (c) 2022 chr_56
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
        val SHOW_AS_ACTION_NEVER = MenuItem.SHOW_AS_ACTION_NEVER
        val SHOW_AS_ACTION_IF_ROOM = MenuItem.SHOW_AS_ACTION_IF_ROOM
        val SHOW_AS_ACTION_ALWAYS = MenuItem.SHOW_AS_ACTION_ALWAYS
        val SHOW_AS_ACTION_WITH_TEXT = MenuItem.SHOW_AS_ACTION_WITH_TEXT
        val SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
    }
}
