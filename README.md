# DialogPlus
[ ![Download](https://api.bintray.com/packages/ma7madfawzy/DialogPlus/com.dialog.plus/images/download.svg) ](https://bintray.com/ma7madfawzy/DialogPlus/com.dialog.plus/_latestVersion)

An Android library that lets you create a sweet interface dialog layout in a simple and easy way ,with different types which you can use easily without any boilerbate code and with a great flexibilty to fit your desired user interface.

![sample](images/Demo2.gif)

## Quick Setup

### 1- Include library

#### Using Gradle
```
dependencies {
implementation  'com.dialog:plus:2+'
}
```
#### Using Maven
```
<dependency>
  <groupId>com.dialog</groupId>
  <artifactId>plus</artifactId>
  <version>2+</version>
  <type>pom</type>
</dependency>

```
## 2- Usage

### 2.1 MESSAGE Dialog:

 ```
new DialogPlus("Message Dialog", "message dialog sample\n Welcome Back")
                .setMessageDialog(new DialogPlus.OnDialogActionClicked()  {// implement methods})
                .show(this.getSupportFragmentManager(), "dialog");
```
### 2.2 CONFIRMATION Dialog:

 ```
 new DialogPlus("Confirmation Dialog", "confirmation dialog message content ...")
                .setOnDialogActionClicked(new DialogPlus.OnDialogActionClicked()  {// implement methods})
                .show(this.getSupportFragmentManager(), "dialog");
```
### 2.3 SUCCESS Dialog:

 ```
 new DialogPlus("Success message content..")
                .setSuccessDialog(new DialogPlus.OnDialogActionClicked()  {// implement methods})
                .show(this.getSupportFragmentManager(), "dialog");
```
### 2.4 ERROR Dialog:

 ```
 new DialogPlus("error dialog content message")
                .setErrorDialog(new DialogPlus.OnDialogActionClicked()  {// implement methods})
                .show(this.getSupportFragmentManager(), "dialog");
```
### 2.5 CODE Dialog:

 ```
 new DialogPlus("Code Dialog", "code dialog sample with send enabled, resend enabled and counter 10 seconds")
                /**(String correct_code, boolean withSend, boolean withResend, int counterSeconds
                   , @ColorInt int codeTextColor,CodeTypeListener codeTypeListener)**/
                .setConfirmCodeDialog("12345", true, true, 10, Color.BLACK, new DialogPlus.CodeTypeListener() {})
                .show(this.getSupportFragmentManager(), "dialog");
```
### 2.6 Multi Options Dialog:

 ```
  new MultiOptionsDialog("Multi Options Dialog Sample Title", titlesList
                , new MultiOptionsDialog.ActionListener() {
            @Override
            public void onActionClicked(String clickedOption) {
                Toast.makeText(MainActivity.this, clickedOption, Toast.LENGTH_SHORT).show();
            }
        }).show(this.getSupportFragmentManager(), "option_dialog_plus");
```
### 2.7 List Dialog:

 ```
 new DialogPlus()
                .setListDialog("list_dialog_test_title", titleStringList, new DialogPlus.DialogListListener() {
                    @Override
                    public void onItemClicked(String title, int index, DialogPlus dialogPlus) {
                        dialogPlus.dismiss();
                    }
                })
                .show(this.getSupportFragmentManager(), "dialog_plus");
```

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
### 4 Customizing:
### 4.1 per use
```
 new DialogPlus("Dialog Title", "dialog content ...")
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
````
#### Integers
````
    <integer name="dialog_content_maxLines">4</integer>
    <integer name="dialog_width_percent">90</integer>
    <integer name="dialog_header_maxLines">1</integer>
````
#### Colors
````
   <color name="colorPrimary">#0698B5</color>
    <color name="colorPrimaryDark">#0694B3</color>
    <color name="colorAccent">@color/carbon_grey_400</color>

    <color name="titleTextColor">@color/dialogPositiveTextColor</color>
    <color name="dialogContentTextColor">@color/colorAccent</color>

    <color name="dialogListItemTextColor">@color/colorAccent</color>

    <color name="dialogTransparent">#00000000</color>

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
            <item name="android:textColor">@color/dialogListItemTextColor</item>
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

    <style name="dialog_negative_button_style" parent="@android:style/TextAppearance">
        <item name="android:text">@string/dialog_negative_text</item>
        <item name="android:textSize">@dimen/dialog_action_button_textSize</item>
        <item name="android:textColor">@color/dialogContentTextColor</item>
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
    
     <style name="dialog_option_button_style" parent="@android:style/TextAppearance">
        <item name="android:text">@string/dialog_positive_text</item>
        <item name="android:textSize">@dimen/dialog_action_button_textSize</item>
        <item name="android:textColor">@color/dialogPositiveTextColor</item>
        <item name="android:textAllCaps">false</item>
        <item name="android:textStyle">normal</item>
    </style>
````

### Happy Coding

## Authors

* [Mohammed Fawzy](https://github.com/ma7madfawzy)
* [Ali Gamal](https://github.com/aligamal-dev)
* [Muhammad Noamany](https://github.com/muhammadnomany25)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

