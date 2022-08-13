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

import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.SubMenu
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import com.github.chr56.android.internal.GROUP_ID
import com.github.chr56.android.internal.HEADER_ICON
import com.github.chr56.android.internal.HEADER_TITLE
import com.github.chr56.android.internal.HEADER_VIEW
import com.github.chr56.android.internal.ICON
import com.github.chr56.android.internal.ITEM_ID
import com.github.chr56.android.internal.ORDER
import com.github.chr56.android.internal.PropertyMapDelegate
import com.github.chr56.android.internal.TITLE

class SubMenuContext(val menuRoot: MenuRoot, val parentMenu: Menu, val currentMenu: SubMenu) {
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
    var icon: Drawable?
        get() = delegate[ICON]
        set(value) { delegate[ICON] = value }

    var headerTitle: CharSequence?
        get() = delegate[HEADER_TITLE]
        set(value) { delegate[HEADER_TITLE] = value }
    var headerIcon: Drawable?
        get() = delegate[HEADER_ICON]
        set(value) { delegate[HEADER_ICON] = value }
    var headerView: View?
        get() = delegate[HEADER_VIEW]
        set(value) { delegate[HEADER_VIEW] = value }

    fun titleRes(@StringRes id: Int) {
        title = menuRoot.context.getString(id)
    }
    fun iconRes(@DrawableRes id: Int) {
        icon = AppCompatResources.getDrawable(menuRoot.context, id)
    }

    fun headerTitleRes(@StringRes id: Int) {
        headerTitle = menuRoot.context.getString(id)
    }
    fun headerIconRes(@DrawableRes id: Int) {
        headerIcon = AppCompatResources.getDrawable(menuRoot.context, id)
    }
}
