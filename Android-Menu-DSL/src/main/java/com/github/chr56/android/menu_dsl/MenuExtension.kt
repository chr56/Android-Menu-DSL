/*
 * Copyright (c) 2022 chr_56
 */

@file:JvmName("MenuExtensions")
package com.github.chr56.android.menu_dsl

import android.os.Build
import android.view.Menu
import android.view.MenuItem

fun Menu.add(cfg: MenuItemCfg.() -> Unit): MenuItem {
    val item = MenuItemCfg().apply(cfg)
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
