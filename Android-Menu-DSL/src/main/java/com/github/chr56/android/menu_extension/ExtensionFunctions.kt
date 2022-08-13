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

@file:JvmName("ExtensionFunctions")

package com.github.chr56.android.menu_extension

import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_NEVER
import android.view.SubMenu
import com.github.chr56.android.internal.ACTION_PROVIDER
import com.github.chr56.android.internal.ALPHABETIC_SHORTCUT
import com.github.chr56.android.internal.CHECKABLE
import com.github.chr56.android.internal.CHECKED
import com.github.chr56.android.internal.CONTENT_DESCRIPTION
import com.github.chr56.android.internal.CUSTOM_ACTIONVIEW
import com.github.chr56.android.internal.ENABLED
import com.github.chr56.android.internal.HEADER_ICON
import com.github.chr56.android.internal.HEADER_TITLE
import com.github.chr56.android.internal.HEADER_VIEW
import com.github.chr56.android.internal.ICON
import com.github.chr56.android.internal.INTENT
import com.github.chr56.android.internal.MENU_ITEM_CLICK_LISTENER
import com.github.chr56.android.internal.NUMERIC_SHORTCUT
import com.github.chr56.android.internal.SHOW_AS_ACTION_FLAG
import com.github.chr56.android.internal.TITLE_CONDENSED
import com.github.chr56.android.internal.TOOLTIP_TEXT
import com.github.chr56.android.internal.VISIBLE
import com.github.chr56.android.menu_model.MenuRoot
import com.github.chr56.android.menu_model.MenuItemContext
import com.github.chr56.android.menu_model.SubMenuContext

//
// Add a MenuItem
//

inline fun Menu.add(
    menuRoot: MenuRoot,
    menuItemContextBlock: MenuItemContext.() -> Unit
): MenuItem {
    val config = MenuItemContext(menuRoot).apply(menuItemContextBlock)
    return this.add(config.groupId, config.itemId, config.order, config.title).applyCfg(config)
}

inline fun Menu.add(
    menuRoot: MenuRoot,
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    menuItemContextBlock: MenuItemContext.() -> Unit
): MenuItem {
    val config = MenuItemContext(menuRoot).apply(menuItemContextBlock)
    return this.add(groupId, itemId, order, title).applyCfg(config)
}

inline fun Menu.add(
    menuRoot: MenuRoot,
    title: CharSequence,
    menuItemContextBlock: MenuItemContext.() -> Unit
): MenuItem {
    val config = MenuItemContext(menuRoot).apply(menuItemContextBlock)
    return this.add(config.groupId, config.itemId, config.order, title).applyCfg(config)
}

fun MenuItem.applyCfg(cfg: MenuItemContext): MenuItem {
    val delegate = cfg.delegate
    for (key in delegate.iterator()) {
        when (key) {
            SHOW_AS_ACTION_FLAG -> setShowAsAction(delegate[SHOW_AS_ACTION_FLAG] ?: SHOW_AS_ACTION_NEVER)
            ICON -> icon = delegate[ICON]

            CONTENT_DESCRIPTION ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    contentDescription = delegate[CONTENT_DESCRIPTION]
                }
            TOOLTIP_TEXT ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tooltipText = delegate[TOOLTIP_TEXT]
                }

            ENABLED -> isEnabled = delegate[ENABLED] ?: true
            VISIBLE -> isVisible = delegate[VISIBLE] ?: true
            CHECKABLE -> isCheckable = delegate[CHECKABLE] ?: false
            CHECKED -> isChecked = delegate[CHECKED] ?: false

            MENU_ITEM_CLICK_LISTENER -> setOnMenuItemClickListener(delegate[MENU_ITEM_CLICK_LISTENER])
            INTENT -> intent = delegate[INTENT]

            TITLE_CONDENSED -> titleCondensed = delegate[TITLE_CONDENSED]

            NUMERIC_SHORTCUT -> numericShortcut = delegate[NUMERIC_SHORTCUT]!!
            ALPHABETIC_SHORTCUT -> alphabeticShortcut = delegate[ALPHABETIC_SHORTCUT]!!

            CUSTOM_ACTIONVIEW -> actionProvider = delegate[CUSTOM_ACTIONVIEW]
            ACTION_PROVIDER -> actionProvider = delegate[ACTION_PROVIDER]
        }
    }
    return this
}

//
// Add a SubMenu
//

inline fun Menu.addSubMenu(
    menuRoot: MenuRoot,
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    SubMenuContextBlock: SubMenuContext.() -> Unit
): SubMenu {
    val subMenu = this.addSubMenu(groupId, itemId, order, title)
    val config = SubMenuContext(menuRoot, this, subMenu).apply(SubMenuContextBlock)
    return subMenu.applyCfg(config)
}

inline fun Menu.addSubMenu(
    menuRoot: MenuRoot,
    title: CharSequence,
    SubMenuContextBlock: SubMenuContext.() -> Unit
): SubMenu {
    val subMenu = this.addSubMenu(title)
    val config = SubMenuContext(menuRoot, this, subMenu).apply(SubMenuContextBlock)
    return subMenu.applyCfg(config)
}

fun SubMenu.applyCfg(cfg: SubMenuContext): SubMenu {
    val delegate = cfg.delegate
    for (key in delegate.iterator()) {
        when (key) {
            ICON -> setIcon(delegate[ICON])
            HEADER_TITLE -> setHeaderTitle(delegate[HEADER_TITLE])
            HEADER_ICON -> setHeaderIcon(delegate[HEADER_ICON])
            HEADER_VIEW -> setHeaderView(delegate[HEADER_VIEW])
        }
    }
    return this
}

//
// Add a MenuItem in SubMenu
//

inline fun SubMenu.add(
    menuRoot: MenuRoot,
    menuItemContextBlock: MenuItemContext.() -> Unit
): MenuItem {
    val config = MenuItemContext(menuRoot).apply(menuItemContextBlock)
    return this.add(config.groupId, config.itemId, config.order, config.title).applyCfg(config)
}

inline fun SubMenu.add(
    menuRoot: MenuRoot,
    groupId: Int,
    itemId: Int,
    order: Int,
    title: CharSequence,
    menuItemContextBlock: MenuItemContext.() -> Unit
): MenuItem {
    val config = MenuItemContext(menuRoot).apply(menuItemContextBlock)
    return this.add(groupId, itemId, order, title).applyCfg(config)
}

inline fun SubMenu.add(
    menuRoot: MenuRoot,
    title: CharSequence,
    menuItemContextBlock: MenuItemContext.() -> Unit
): MenuItem {
    val config = MenuItemContext(menuRoot).apply(menuItemContextBlock)
    return this.add(config.groupId, config.itemId, config.order, title).applyCfg(config)
}
