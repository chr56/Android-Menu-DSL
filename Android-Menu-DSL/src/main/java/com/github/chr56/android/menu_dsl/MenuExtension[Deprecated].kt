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

@file:JvmName("MenuExtensions")
package com.github.chr56.android.menu_dsl

import android.os.Build
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu

@Deprecated("generated codes too long")
inline fun Menu.add(menuContext: MenuContext, cfg: MenuItemCfg.() -> Unit): MenuItem {
    val item = MenuItemCfg(menuContext).apply(cfg)
    return this.add(
        item.groupId,
        item.itemId,
        item.order,
        item.title
    ).apply {
        item.titleCondensed?.also { titleCondensed = it }
        item.icon?.also { icon = it }
        setShowAsAction(item.showAsActionFlag)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            item.tooltipText?.also { tooltipText = it }
            item.contentDescription?.also { contentDescription = it }
        }
        item.enabled.also { isEnabled = it }
        item.visible.also { isVisible = it }
        item.checkable.also { isCheckable = it }
        item.checked.also { isChecked = it }

        item.menuItemClickListener?.also { setOnMenuItemClickListener(it) }
        item.intent?.also { intent = it }

        item.numericShortcut?.also { numericShortcut = it }
        item.alphabeticShortcut?.also { alphabeticShortcut = it }

        item.customActionView?.also { actionView = it }
        item.actionProvider?.also { actionProvider = it }
    }
}

@Deprecated("generated codes too long")
inline fun Menu.addSubMenu(menuContext: MenuContext, cfg: SubMenuCfg.() -> Unit): SubMenu {
    val item = SubMenuCfg(menuContext).apply(cfg)
    return this.addSubMenu(
        item.groupId,
        item.itemId,
        item.order,
        item.title
    ).apply {
        item.icon?.also { setIcon(it) }
        item.headerIcon?.also { setHeaderIcon(it) }
        item.headerTitle?.also { setHeaderTitle(it) }
        item.headerView?.also { setHeaderView(it) }
    }
}
