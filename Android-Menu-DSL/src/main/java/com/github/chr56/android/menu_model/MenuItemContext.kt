/*
 *  Copyright (C) 2022 chr_56
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.github.chr56.android.menu_model

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.ActionProvider
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import com.github.chr56.android.internal.ACTION_PROVIDER
import com.github.chr56.android.internal.ALPHABETIC_SHORTCUT
import com.github.chr56.android.internal.CHECKABLE
import com.github.chr56.android.internal.CHECKED
import com.github.chr56.android.internal.CONTENT_DESCRIPTION
import com.github.chr56.android.internal.CUSTOM_ACTIONVIEW
import com.github.chr56.android.internal.ENABLED
import com.github.chr56.android.internal.GROUP_ID
import com.github.chr56.android.internal.ICON
import com.github.chr56.android.internal.INTENT
import com.github.chr56.android.internal.ITEM_ID
import com.github.chr56.android.internal.MENU_ITEM_CLICK_LISTENER
import com.github.chr56.android.internal.NUMERIC_SHORTCUT
import com.github.chr56.android.internal.ORDER
import com.github.chr56.android.internal.PropertyMapDelegate
import com.github.chr56.android.internal.SHOW_AS_ACTION_FLAG
import com.github.chr56.android.internal.TITLE
import com.github.chr56.android.internal.TITLE_CONDENSED
import com.github.chr56.android.internal.TOOLTIP_TEXT
import com.github.chr56.android.internal.VISIBLE

class MenuItemContext(val menuRoot: MenuRoot) {

    internal val delegate = PropertyMapDelegate()

    var itemId: Int
        get() = delegate[ITEM_ID] ?: Menu.NONE
        set(value) { delegate[ITEM_ID] = value }
    var groupId: Int
        get() = delegate[GROUP_ID] ?: Menu.NONE
        set(value) { delegate[GROUP_ID] = value }
    var order: Int
        get() = delegate[ORDER] ?: Menu.NONE
        set(value) { delegate[ORDER] = value }

    var title: CharSequence?
        get() = delegate[TITLE]
        set(value) { delegate[TITLE] = value }

    var titleCondensed: CharSequence?
        get() = delegate[TITLE_CONDENSED]
        set(value) { delegate[TITLE_CONDENSED] = value }
    var icon: Drawable?
        get() = delegate[ICON]
        set(value) { delegate[ICON] = value }
    var showAsActionFlag: Int
        get() = delegate[SHOW_AS_ACTION_FLAG] ?: MenuItem.SHOW_AS_ACTION_NEVER
        set(value) { delegate[SHOW_AS_ACTION_FLAG] = value }

    var tooltipText: CharSequence?
        get() = delegate[TOOLTIP_TEXT]
        set(value) { delegate[TOOLTIP_TEXT] = value }
    var contentDescription: CharSequence?
        get() = delegate[CONTENT_DESCRIPTION]
        set(value) { delegate[CONTENT_DESCRIPTION] = value }

    var enabled: Boolean
        get() = delegate[ENABLED] ?: true
        set(value) { delegate[ENABLED] = value }
    var visible: Boolean
        get() = delegate[VISIBLE] ?: true
        set(value) { delegate[VISIBLE] = value }
    var checkable: Boolean
        get() = delegate[CHECKABLE] ?: false
        set(value) { delegate[CHECKABLE] = value }
    var checked: Boolean
        get() = delegate[CHECKED] ?: false
        set(value) { delegate[CHECKED] = value }

    var menuItemClickListener: MenuItem.OnMenuItemClickListener?
        get() = delegate[MENU_ITEM_CLICK_LISTENER]
        set(value) { delegate[MENU_ITEM_CLICK_LISTENER] = value }

    @Suppress("NOTHING_TO_INLINE")
    inline fun onClick(noinline callback: (MenuItem) -> Boolean) {
        menuItemClickListener = MenuItem.OnMenuItemClickListener(callback)
    }

    var intent: Intent?
        get() = delegate[INTENT]
        set(value) { delegate[INTENT] = value }

    var numericShortcut: Char?
        get() = delegate[NUMERIC_SHORTCUT]
        set(value) { delegate[NUMERIC_SHORTCUT] = value }
    var alphabeticShortcut: Char?
        get() = delegate[ALPHABETIC_SHORTCUT]
        set(value) { delegate[ALPHABETIC_SHORTCUT] = value }

    var customActionView: View?
        get() = delegate[CUSTOM_ACTIONVIEW]
        set(value) { delegate[CUSTOM_ACTIONVIEW] = value }
    var actionProvider: ActionProvider?
        get() = delegate[ACTION_PROVIDER]
        set(value) { delegate[ACTION_PROVIDER] = value }

    fun titleRes(@StringRes id: Int) {
        title = menuRoot.context.getString(id)
    }
    fun iconRes(@DrawableRes id: Int) {
        icon = AppCompatResources.getDrawable(menuRoot.context, id)
    }
}
