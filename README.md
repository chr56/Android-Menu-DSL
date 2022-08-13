# Android-Menu-DSL

[![](https://jitpack.io/v/chr56/Android-Menu-DSL.svg)](https://jitpack.io/#chr56/Android-Menu-DSL)

An kotlin Android library to define menu items using kotlin DSL.

### Usage

```groovy
dependencies {
    implementation("com.github.chr56:Android-Menu-DSL:Tag")
}
```

Please also add jitpack to repositories list.

#### Example

```kotlin
 override fun onCreateOptionsMenu(menu: Menu): Boolean {
    attach(from = menu) {
        menuItem {
            itemId = R.id.setting
            title = getString(R.string.title_setting)
            icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_settings_white_24dp)
            showAsActionFlag = SHOW_AS_ACTION_NEVER
        }
        menuItem {
            itemId = R.id.about
            titleRes(R.string.title_about)
            icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_info_outline_white_24dp)
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
```

### Changelog

**0.1.0**
- use new way to create menu items to reduce code generated
- slightly redesign api & reorganize package
- fully support SubMenu and its DSL

**0.0.4**

- remove deprecated method
- return MenuItem for `MenuContext.menuItem()`
- return Submenu for `MenuContext.submenu()`

**0.0.3**

- rename function `menu` to `attach`
- require `menuContext` for all Menu extension functions

**0.0.2**

- add `onClick { }` in MenuItemCfg
- implement SubMenu (Experiental)
