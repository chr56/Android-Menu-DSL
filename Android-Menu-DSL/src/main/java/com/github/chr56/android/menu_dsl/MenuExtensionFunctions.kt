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

@file:JvmName("MenuExtensionFunctions")

package com.github.chr56.android.menu_dsl

import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_NEVER

inline fun Menu.add(menuContext: MenuContext, menuItemContextBlock: MenuItemContext.() -> Unit): MenuItem {
    val config = MenuItemContext(menuContext).apply(menuItemContextBlock)
    return this.add(
        config.groupId,
        config.itemId,
        config.order,
        config.title
    ).applyCfg(config)
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
