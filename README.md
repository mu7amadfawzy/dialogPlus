# DialogPlus
[ ![Download](https://api.bintray.com/packages/ma7madfawzy/DialogPlus/com.dialog.plus/images/download.svg) ](https://bintray.com/ma7madfawzy/DialogPlus/com.dialog.plus/_latestVersion)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-DialogPlus-green.svg?style=flat )]( https://android-arsenal.com/details/1/7899 )

An Android library that lets you create a sweet interface dialog layout in a simple and easy way ,with different types which you can use easily without any boilerbate code and with a great flexibilty to fit your desired user interface.

The Dialog provides many types such as Message Dialog, Confirmation Dialog(a yes/no dialog), Code Entry Dialog, Success Dialog, Error Dialog, Multi Options Dialog, List Dialog, Rating Dialog, Year Picker, Month Picker Dialog and Country Picker Dialog.
![sample](files/demo.gif)

## Quick Setup

### 1- Include library

#### Using Gradle
```
dependencies {
implementation  'com.dialog:plus:4+'
}
```
#### Using Maven
```
<dependency>
  <groupId>com.dialog</groupId>
  <artifactId>plus</artifactId>
  <version>4+</version>
  <type>pom</type>
</dependency>

```
## 2- Usage

### 2.1 MESSAGE Dialog:

 ```
 new DialogPlusBuilder("Message Dialog", "message dialog_plus sample\n Welcome Back")
                //@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBgColor
                .setTexts("alright")
                .setBackgrounds(R.color.colorPrimary, R.color.colorAccent)
                .buildMessageDialog(new DialogListener() {//implement functions
                })
                .show(this.getSupportFragmentManager(), "Message Dialog");
```
### 2.2 CONFIRMATION Dialog:

 ```
  new DialogPlusBuilder("Confirmation Dialog", "confirmation dialog_plus message content ...")
                .setTexts("confirm", "cancel")
                .setBackgroundColors(R.color.colorPrimary, R.color.white, R.color.colorPrimary)
                .buildConfirmationDialog(false,new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Confirmation Dialog");
```
### 2.3 SUCCESS Dialog:

 ```
  new DialogPlusBuilder("Success message content..")
                .setSuccessDialog("Cool")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .build(new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Success Dialog");
```
### 2.4 ERROR Dialog:

 ```
 new DialogPlusBuilder("Error Dialog content message")
                .setErrorDialog("Peace")
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .build(new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Error Dialog");
```
### 2.5 CODE Dialog:

 ```
  new DialogPlusBuilder("Code Dialog", "code dialog_plus sample with send enabled, resend enabled and counter 10 seconds")
                .setTexts("Confirm")
                .setDialogCodeTextColor(getResources().getColor(R.color.colorPrimaryDark))
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                .buildCodeDialog("12345", 60, true, true, new DialogListener() {
                    // implement methods
                })
                .show(this.getSupportFragmentManager(), "Code Dialog");
```
### 2.6 Multi Options Dialog:

 ```
   new DialogPlusBuilder().setTitle("Multi Options Dialog Sample Title")
                .setHeaderBgColor(R.color.dialogTransparent).setHeaderTextColor(R.color.black)
                .buildMultiOptionsDialog(getOptions()
                        , new MultiOptionsDialog.ActionListener() {
                            @Override
                            public void onActionClicked(String clickedOption) {
                                Toast.makeText(MainActivity.this, clickedOption, Toast.LENGTH_SHORT).show();
                            }
                        }).show(this.getSupportFragmentManager(), "Multi Options Dialog");
```
### 2.7 List Dialog:

 ```
  new DialogPlusBuilder().setTitle("List Dialog")
                .buildListDialog(getListItems(), new DialogPlus.DialogListListener() {
                    @Override
                    public void onItemClicked(String title, int index, DialogPlus dialogPlus) {
                        super.onItemClicked(title, index, dialogPlus);
                        Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
                    }
                })
                .show(this.getSupportFragmentManager(), "List Dialog");
```
### 2.8 Year Picker Dialog:

 ```
 new DialogPlusBuilder().setHeaderBgDrawable(R.drawable.bg_header).buildYearPickerDialog(pickedYear ->
                Toast.makeText(this, "picked year: " + pickedYear, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Year Picker");
```
### 2.9 Month Picker Dialog:

 ```
   new DialogPlusBuilder().setTitle("pick month").setHeaderBgDrawable(R.drawable.bg_header).buildYearPickerDialog(pickedYear ->
                Toast.makeText(this, "picked year: " + pickedYear, Toast.LENGTH_SHORT).show())
                .show(getSupportFragmentManager(), "Month Picker");
```
### 2.10 Rate Dialog:

 ```
     new DialogPlusBuilder("Rating Dialog", "Rate dialog_plus message content ...")
                .setTexts("rate", "cancel")
                .buildRatingDialog(1.7f, false, new DialogPlus.DialogRateListener() {
                    @Override
                    public void onRateGiven(float rate, DialogPlus dialogPlus) {
                        Toast.makeText(MainActivity.this, "rated with " + rate, Toast.LENGTH_SHORT).show();
                            }
                })
                .show(this.getSupportFragmentManager(), "Rating Dialog");
```
### 2.11 Country Picker Dialog:

 ```
     new DialogPlusBuilder().setTitle("Countries List Dialog")
                .buildCountriesListDialog(true,new DialogPlus.CountriesDialogListener() {
                    @Override
                    public void onItemClicked(CountryDataModel countryDataModel, DialogPlus dialogPlus) {
                        super.onItemClicked(countryDataModel, dialogPlus);
                        Toast.makeText(MainActivity.this, "country:" + countryDataModel.getName() + " ,Code: " + countryDataModel.getPhone_code(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show(this.getSupportFragmentManager(), "Countries List Dialog");
```
#### Note you can use CountryRepo to retrieve any country in any language using the country code
``` String arabicStr = new CountryRepo(MainActivity.this).getCounty("ar", 20).getName(); ```

### 3 Listeners:

 ```setDialogActionListener(DialogPlus.DialogActionListener)```  
#### Used with MESSAGE ,CONFIRMATION SUCCESS and ERROR dialogs

##### You can only implement onPositive()
```
.setDialogActionListener(new DialogPlus.DialogActionListener() {
                    @Override
                    public void onPositive(DialogPlus dialogPlus) {

                    }
                })
});
````
##### You can also Override other methods onNegative(),onWrongCode()
````
setDialogActionListener(new DialogPlus.DialogActionListener() {
                    @Override
                    public void onPositive(DialogPlus dialogPlus) {

                    }
                    @Override
                    public void onNegative(DialogPlus dialogPlus) {
                        super.onNegative(dialogPlus);
                    }

                    @Override
                    public void onWrongCode(DialogPlus dialogPlus) {
                        super.onWrongCode(dialogPlus);
                    }
                })
});
````
#### ```setCodeTypeListener(DialogPlus.CodeTypeListener)```  Used only with CODE dialog

##### You can Override other methods ```onNegative()```
````
setCodeTypeListener(new DialogPlus.CodeTypeListener() {
                    @Override
                    public void onSuccess(DialogPlus dialogPlus) {

                    }

                    @Override
                    public void onResend(DialogPlus dialogPlus) {

                    }

                    @Override
                    public void onWrongCode(DialogPlus dialogPlus) {

                    }

                   /**you may override timeUp if there's a specific scenario you want to handle*/
                    @Override
                    public void onTimeUp(DialogPlus dialogPlus) {
                        super.onTimeUp(dialogPlus);
                    }
                });
````
#### ```setRateListener(DialogPlus.DialogRateListener)```  Used only with RATE dialog

### 4 Customizing:
### 4.1 per use
```
 new DialogPlusBuilder("Dialog Title", "dialog content ...")
                 .setTitle(null)//will hide the header view
                 .setContent(null)//will hide the content view
                //(@ColorRes int positiveBackground, @ColorRes int negativeColorRes, @ColorRes int headerBgColor)
                .setBackgroundColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                
                //(@DrawableRes int positiveBackground, @DrawableRes int negativeBackground, @DrawableRes int headerBackground)
                .setBackgrounds(R.drawable.cross, R.drawable.bg_header, R.drawable.checked)
                
                //(@ColorRes int positiveTextColor, @ColorRes int negativeTextColor, @ColorRes int headerTextColor)
                .setTextColors(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimary)
                
                //(@ColorRes int primaryColor)--> sets the positiveBgColor and the headerBgColor
                .setPrimaryBgColor(R.color.colorPrimary)
                //(@ColorRes int primaryColor)--> sets the negativeBgColor
                .setSecondaryBgColor(R.color.colorAccent)
                
                //(@ColorRes int primaryColor)--> sets the positiveTextColor 
                .setPrimaryTextColor(R.color.colorPrimary)
                //(@ColorRes int secondaryColor)--> sets the negativeTextColor
                .setSecondaryBgColor(R.color.colorAccent)

                //(@ColorRes int primaryColor)--> sets the positiveBgDrawable and the headerBgDrawable
                .setPrimaryDrawable(R.drawable.bg_header)
                //(@ColorRes int secondaryDrawable)--> sets the negativeTextDrawable
                .setSecondaryBgDrawable(R.drawable.bg_header)
                
                //(@ColorRes int headerBgColor)
                .setHeaderBgColor(R.color.colorAccent)
                //(@DrawableRes int headerBgDrawable)
                .setHeaderBgDrawable(R.drawable.bg_header)
                
                // @ColorInt int colorInt
                .setCodeTextColor(Color.BLACK)       
````
### 4.2 Override any of these resources to set the dialog attributes for whole project
#### Dimensions
````
    <dimen name="dialog_corner_radius">15dp</dimen>
    <dimen name="dialog_margin">@dimen/_30sdp</dimen>
    <dimen name="dialog_timeup_text_size">@dimen/_10ssp</dimen>

    <dimen name="dialog_header_height">@dimen/_40sdp</dimen>
    <dimen name="dialog_header_text_size">@dimen/_14ssp</dimen>
    <dimen name="dialog_header_padding">@dimen/_8sdp</dimen>

    <dimen name="dialog_content_margin">@dimen/_15sdp</dimen>
    <dimen name="dialog_content_message_text_size">@dimen/_12ssp</dimen>

    <dimen name="dialog_list_item_text_size">@dimen/_12ssp</dimen>
    <dimen name="dialog_list_search_bar_text_size">@dimen/_11ssp</dimen>
    <dimen name="dialog_list_search_bar_padding">@dimen/_5sdp</dimen>
    <dimen name="dialog_list_search_bar_corner">0dp</dimen>
    <dimen name="dialog_list_search_bar_elevation">0dp</dimen>
    <dimen name="dialog_list_Search_bar_margin">0dp</dimen>

    <dimen name="dialog_country_flag_width">@dimen/_25sdp</dimen>
    <dimen name="dialog_country_flag_margin">@dimen/_8sdp</dimen>
    <dimen name="dialog_country_row_padding">@dimen/_2sdp</dimen>

    <dimen name="dialog_action_buttons_margin">@dimen/_10sdp</dimen>
    <dimen name="dialog_action_button_corner">50dp</dimen>
    <dimen name="dialog_action_buttons_height">@dimen/_35sdp</dimen>

    <!--    space for showing elevation-->
    <dimen name="dialog_action_buttons_bottom_margin">2dp</dimen>

    <dimen name="dialog_action_button_textSize">@dimen/_12ssp</dimen>

    <dimen name="dialog_positive_button_elevation">1dp</dimen>

    <dimen name="dialog_negative_button_stroke">1dp</dimen>
    <dimen name="dialog_negative_button_elevation">0dp</dimen>

    <dimen name="dialog_close_icon_radius">@dimen/_16sdp</dimen>
    <dimen name="dialog_zero">0dp</dimen>
    <dimen name="dialog_code_entry_margin">@dimen/_8sdp</dimen>
    <dimen name="dialog_elevation">1dp</dimen>

    <dimen name="dialog_options_header_padding">@dimen/_7sdp</dimen>

    <dimen name="dialog_list_row_margin_top">@dimen/_4sdp</dimen>
    <dimen name="dialog_list_row_margin_start">@dimen/_8sdp</dimen>
    <dimen name="dialog_list_separator_margin">@dimen/_16sdp</dimen>

    <dimen name="dialog_rating_bar_margin">@dimen/_15sdp</dimen>
    <dimen name="dialog_image_width">@dimen/_150sdp</dimen>
````
#### Integers
````
    <integer name="dialog_content_maxLines">4</integer>
    <integer name="dialog_width_percent">90</integer>
    <integer name="dialog_month_year_width_percent">60</integer>
    <integer name="dialog_header_maxLines">1</integer>
````
#### Colors
````
    <color name="colorPrimary">#0698B5</color>
    <color name="colorPrimaryDark">#05839C</color>
    <color name="colorAccent">#000</color>

    <color name="titleTextColor">@color/dialogPositiveTextColor</color>
    <color name="dialogContentTextColor">@color/colorAccent</color>

    <color name="dialogListItemTextColor">@color/colorPrimary</color>
    <color name="dialogListSearchBarBackgroundColor">@color/carbon_grey_200</color>
    <color name="dialogListSearchBarTextColor">@color/carbon_grey_600</color>

    <color name="dialogTransparent">#00000000</color>
    <color name="dialogBlack">#000000</color>dialogBlack

    <color name="dialogNegativeBgColor">@color/dialogTransparent</color>
    <color name="dialogNegativeTextColor">@color/carbon_red_800</color>

    <color name="dialogPositiveBgColor">@color/colorPrimary</color>
    <color name="dialogPositiveTextColor">@color/dialog_white</color>

    <color name="dialogTimeUpTextColor">@color/dialogNegativeTextColor</color>

    <color name="dialogActionButtonsStrokeColor">@color/colorAccent</color>

    <color name="dialog_send_background">@color/colorPrimary</color>
    <color name="dialog_resend_text_color">@color/colorPrimary</color>

    <color name="dialog_white">#ffffff</color>

    <color name="dialog_error_button_background">@color/carbon_red_600</color>
    <color name="dialog_success_button_background">@color/carbon_green_500</color>

    <color name="dialog_option_background">@color/carbon_grey_200</color>
    <color name="dialog_option_text_color">@color/colorPrimary</color>
    <color name="dialog_option_close_text_color">@color/colorPrimaryDark</color>
    <color name="dialog_option_title_text_color">#000000</color>
    <color name="dialog_action_seperator">@color/carbon_grey_400</color>

    <color name="dialog_month_year_action_background">#00000000</color>
    <color name="dialog_month_year_action_text_color">@color/colorPrimary</color>

    <color name="dialog_rating_bar_active_color">@color/colorPrimary</color>
    <color name="dialog_rating_bar_accent_color">@color/colorAccent</color>
    <color name="dialog_code_text_color">@color/dialogBlack</color>
````

#### Strings
````
    <string name="dialog_negative_text">Cancel</string>
    <string name="dialog_ok_text">Ok</string>
    <string name="dialog_positive_text">Confirm</string>
    <string name="dialog_resend">Resend</string>
    <string name="dialog_time_up">Time up</string>
    <string name="dialog_incomplete_code_msg">Please enter complete code</string>
    <string name="dialog_options_close">Close</string>
    <string name="dialog_pick_year_title">Choose year</string>
    <string name="dialog_pick_month_title">Choose month</string>
    <string name="dialog_list_search_hint">type here to search</string>
````

#### Styles
````
    <style name="dialog_header_style" parent="@android:style/TextAppearance">
        <item name="android:textSize">@dimen/dialog_header_text_size</item>
        <item name="android:textColor">@color/titleTextColor</item>
        <item name="android:textAllCaps">false</item>
        <item name="android:textStyle">bold</item>
    </style>

    <style name="dialog_content_message_style" parent="@android:style/TextAppearance">
        <item name="android:textSize">@dimen/dialog_content_message_text_size</item>
        <item name="android:textColor">@color/dialogContentTextColor</item>
        <item name="android:textStyle">normal</item>
        <item name="android:textAllCaps">false</item>
    </style>

    <style name="dialog_list_item_text_style" parent="@android:style/TextAppearance">
        <item name="android:textSize">@dimen/dialog_list_item_text_size</item>
        <item name="android:textColor">@color/dialogListSearchBarTextColor</item>
        <item name="android:textStyle">normal</item>
        <item name="android:textAllCaps">false</item>
    </style>

    <style name="dialog_list_Search_bar_text_style" parent="@android:style/TextAppearance">
        <item name="android:textSize">@dimen/dialog_list_search_bar_text_size</item>
        <item name="android:textColor">@color/dialogListSearchBarTextColor</item>
        <item name="android:textStyle">normal</item>
        <item name="android:textAllCaps">false</item>
    </style>

    <style name="dialog_positive_button_style" parent="@android:style/TextAppearance">
        <item name="android:text">@string/dialog_positive_text</item>
        <item name="android:textSize">@dimen/dialog_action_button_textSize</item>
        <item name="android:textColor">@color/dialogPositiveTextColor</item>
        <item name="android:textAllCaps">false</item>
        <item name="android:textStyle">normal</item>
    </style>

    <style name="dialog_option_button_style" parent="@android:style/TextAppearance">
        <item name="android:text">@string/dialog_positive_text</item>
        <item name="android:textSize">@dimen/dialog_action_button_textSize</item>
        <item name="android:textColor">@color/dialogPositiveTextColor</item>
        <item name="android:textAllCaps">false</item>
        <item name="android:textStyle">normal</item>
    </style>

    <style name="dialog_negative_button_style" parent="@android:style/TextAppearance">
        <item name="android:text">@string/dialog_negative_text</item>
        <item name="android:textSize">@dimen/dialog_action_button_textSize</item>
        <item name="android:textColor">@color/dialogNegativeTextColor</item>
        <item name="android:textStyle">normal</item>
        <item name="android:maxLines">@integer/dialog_content_maxLines</item>
    </style>

    <style name="dialog_error_button_style" parent="@android:style/TextAppearance">
        <item name="android:text">@string/dialog_ok_text</item>
        <item name="android:textSize">@dimen/dialog_action_button_textSize</item>
        <item name="android:textAllCaps">false</item>
        <item name="android:textColor">@color/dialog_white</item>
        <item name="android:textStyle">normal</item>
    </style>

    <style name="dialog_success_button_style" parent="@android:style/TextAppearance">
        <item name="android:text">@string/dialog_ok_text</item>
        <item name="android:textSize">@dimen/dialog_action_button_textSize</item>
        <item name="android:textAllCaps">false</item>
        <item name="android:textColor">@color/dialog_white</item>
        <item name="android:textStyle">normal</item>
    </style>

    <style name="dialog_month_year_picker_text_style">
        <item name="android:textSize">@dimen/_12ssp</item>
        <item name="colorControlNormal">#00000000</item>
        <item name="android:textColorPrimary">#000</item>
    </style>

    <style name="dialog_rating_bar_style" parent="Widget.AppCompat.RatingBar">
        <item name="colorControlActivated">@color/dialog_rating_bar_active_color</item>
        <item name="colorControlNormal">@color/dialog_rating_bar_accent_color</item>
    </style>
````

### Happy Coding

## Authors

* [Mohammed Fawzy](https://github.com/ma7madfawzy)
* [Ali Gamal](https://github.com/aligamal-dev)
* [Muhammad Noamany](https://github.com/muhammadnomany25)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

