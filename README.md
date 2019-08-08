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
#### setCodeTypeListener(DialogPlus.CodeTypeListener)```  Used only with CODE dialog

##### You can Override other methods onNegative(),onWrongCode()
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
    <dimen name="dialog_header_height">@dimen/_40sdp</dimen>
    <dimen name="dialog_header_text_size">@dimen/_14ssp</dimen>
    <dimen name="action_button_corner">@dimen/_20sdp</dimen>
    <dimen name="dialog_action_buttons_height">@dimen/_35sdp</dimen>
    <dimen name="dialog_margin">@dimen/_30sdp</dimen>
    <dimen name="dialog_content_margin">@dimen/_25sdp</dimen>
    <dimen name="dialog_timeup_text_size">@dimen/_10ssp</dimen>
    <dimen name="dialog_corner_radius">@dimen/_8sdp</dimen>
    <dimen name="dialog_content_message_text_size">@dimen/_12ssp</dimen>
    <dimen name="dialog_action_buttons_margin">@dimen/_15sdp</dimen>
    <dimen name="dialog_header_padding">@dimen/_8sdp</dimen>
    <integer name="dialog_width_percent">90</integer>
   
````
#### Colors
````
    <color name="titleTextColor">@color/dialogPositiveTextColor</color>
    <color name="dialogContentTextColor">@color/colorAccent</color>

    <color name="dialogNegativeBgColor">#00000000</color>
    <color name="dialogPositiveBgColor">@color/colorPrimary</color>

    <color name="dialogNegativeTextColor">@color/carbon_red_800</color>
    <color name="dialogPositiveTextColor">@color/carbon_white</color>

    <color name="dialogTimeUpTextColor">@color/dialogNegativeTextColor</color>

    <color name="dialogActionButtonsStrokeColor">@color/colorAccent</color>
    <color name="dialog_send_background">@color/colorPrimary</color>
    <color name="dialog_resend_text_color">@color/colorPrimary</color>
   
````

#### Strings
````
   <string name="dialog_cancel">Cancel</string>
    <string name="dialog_ok">Ok</string>
    <string name="dialog_confirm">Confirm</string>
    <string name="dialog_resend">Resend</string>
    <string name="dialog_time_up">Time up</string>
    <string name="dialog_incomplete_code_msg">Please enter complete code</string>
   
````

### Happy Coding

## Authors

* [Mohammed Fawzy](https://github.com/ma7madfawzy)
* [Ali Gamal](https://github.com/aligamal-dev)
* [Muhammad Noamany](https://github.com/muhammadnomany25)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

