# Android-Menu-DSL

An kotlin Android library to define menu items using kotlin DSL.

### example
```kotlin
 override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menu(from = menu) {
        menuItem {
            itemId = R.id.setting
            title = getString(R.string.title_setting)
            icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_settings_white_24dp)
            showAsActionFlag = SHOW_AS_ACTION_NEVER
        }
        menuItem {
            itemId = R.id.about
            icon = AppCompatResources.getDrawable(this@MainActivity,R.drawable.ic_info_outline_white_24dp)
            titleRes(R.string.title_about,this@MainActivity)
            showAsActionFlag = SHOW_AS_ACTION_NEVER
        }
    }
}
```