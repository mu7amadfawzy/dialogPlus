# ExpandableLayout
[ ![Download](https://api.bintray.com/packages/ma7madfawzy/expandableLayout/com.widget.expandableLayout/images/download.svg) ](https://bintray.com/ma7madfawzy/expandableLayout/com.widget.expandableLayout/_latestVersion)

An Android library that lets you create an expandable layout in a simple and easy way in which you can use the default header and content OR pass your custom layout and just expand and collapse magic is all ready.

![sample](images/Demo2.gif)r

## Quick Setup

### 1- Include library

#### Using Gradle
```
dependencies {
implementation  'com.widget:expandableLayout:3+'
}
```
#### Using Maven
```
<dependency>
  <groupId>com.widget</groupId>
  <artifactId>expandableLayout</artifactId>
  <version>3+</version>
  <type>pom</type>
</dependency>

```
## 2- Usage

### 2.1 XML Layout:

 ```
<widget.com.expandablelayout.ExpandableLayout
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 <!--expand/collabse duration ,default 300-->
 app:duration="400"
 <!--default false-->
 app:hideArrow="true"
 app:arrow_icon="@drawable/arrow_down"
 app:header_padding="16dp"
 app:content_padding="10dp"
 <!--default false-->
 app:startExpanded="true"
  <!--default 0-->
 app:pinnedLineHeight="15dp"
 <!--use your own custom layout-->
 app:content_layout="@layout/custom_content"
 app:header_layout="@layout/layout_expandable_header"
 <!--Or use default layout which is a TextView-->
 app:exp_title="Header default Text sample"
 app:exp_content="Content Text Sample"
 <!--fonts is the folder name in Assets-->
 app:header_font="fonts/fontName.ttf"
 app:content_font="fonts/fontName.ttf"'
 app:header_color="@color/colorAccentDark"
 app:content_color="@color/colorAccent"
 app:header_padding="10dp"
 app:content_padding="14dp"
 app:header_text_style="italic"
 app:content_style="bold"/> 

```
#### You can use the default HeaderTV and ContentTV:

##### ````exp_title```` sets the text of the headerTV .
##### ````header_color```` sets the textColor of the headerTV.
##### ````arrow_icon```` sets the resource of the arrowBtn (which is visible with using the default headerTV). 

##### ````content````   sets the text of the contentTV.
##### ````content_color```` sets the textColor of the contentTV.

##### ````duration```` sets the duration of the collabse and expand animation.

#### Or you can use set a custom header or a custom content:

##### ````header_layout````   sets the declared layout resource as the header layout.
##### ````content_layout```` sets the declared layout resource as the content layout.

##### ````pinnedLineHeight```` define collapsed content minimum height.

#### Use ```toggle()``` to reverse the state, and use isExpanded() to check if it was expanded or not.

#### Use ```refresh()``` to remain the state(in case expanded while custom content includes RecyclerView whose data were updated         then trigging refresh() will help the expandable sets the expand height well).

#### ```setOnExpandedListener``` that can be used to listen to state change:
````
expandableLayout.setOnExpandedListener(new OnExpandedListener() {
    @Override
    public void onExpandChanged(View view, boolean isExpanded) {
        //TODO handle onExpandChanged
    }
});
````

### 2.2 Dynamically:

#### In Java:

##### Default HeaderTV and ContentTV
````
 ExpandableLayout expandableLayout = new ExpandableLayout(context)
                .setHeaderTitle("Added By Java", Color.BLACK)
                .setDefaultContent("Content xxx", Color.BLUE)
                .setArrowDrawable(ContextCompat.getDrawable(this, R.drawable.arrow_down));
````

##### Custom HeaderTV OR ContentTV
````
expandableLayout.setHeaderLayout(R.layout.custom_header);
                .setContentLayout(R.layout.custom_content);
````
#### In Kotlin:

##### Default HeaderTV and ContentTV
````
var expandableLayout = ExpandableLayout(context)
                 .setDefaultHeaderTitle("Added Through Kotlin")
                 .setDefaultContentTitle("Content xxx")
                 .setArrowDrawable(R.drawable.arrow_ic)
````

##### Custom HeaderTV OR ContentTV
````
expandableLayout.setHeaderLayout(R.layout.custom_header)
                .setContentLayout(R.layout.custom_content)
````
##### Adding the layout to container view
````
container.addView(expandableLayout)
````
##### Then you can trigger your custom layouts using:
###### For DataBinding lovers 
````
expandable.getHeaderLayoutBinding();//returns ViewDataBinding which can be cast to your layout binding Impl class
expandable.getContentLayoutBinding();
````
###### Or just a simple view
````
expandable.getHeaderLayoutView();
expandable.getContentLayoutView();
````
### 2.3 In RecyclerView:
#### In order to setup using in RecyclerView call ```ExpandableLayout.onAttachedToRecycler()``` before binding items of the Adapter.
##### In order to enable one expanded per time in recyclerViewAdaper.onBind() call ``` setRecyclerItem(linearLayoutManager,itemPosition)``` with the RecyclerView's layoutManger(weather ```GridLayoutManager``` or ```LinearLayoutManager```)
````
expandable.setRecyclerItem(linearLayoutManager, getAdapterPosition());
````

### Happy Coding

## Authors

* [Mohammed Fawzy](https://github.com/ma7madfawzy)
* [Ali Gamal](https://github.com/aligamal-dev)
* [Muhammad Noamany](https://github.com/muhammadnomany25)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

