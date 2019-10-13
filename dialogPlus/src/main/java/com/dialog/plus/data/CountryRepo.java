package com.dialog.plus.data;

import android.content.Context;

import com.dialog.plus.ui.CountryDataModel;
import com.dialog.plus.utils.AssetUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;

import static com.dialog.plus.utils.ConfigUtils.getCurrentLocale;

/**
 * Created by Fawzy on 02,October,2019
 * ma7madfawzy@gmail.com
 **/
public class CountryRepo {
    private Context context;

    public CountryRepo(Context context) {
        this.context = context;
    }

    /**
     * @param iso2Language is the language code in two characters like en for english or ar for arabic
     */
    public CountryDataModel getCounty(String iso2Language, String countryCode) {
        List<CountryDataModel> countriesList = getCountriesList(getCountriesStr(iso2Language));
        for (CountryDataModel dataModel : countriesList) {
            if (dataModel.getCode().equals(countryCode))
                return dataModel;
        }
        return null;
    }

    public List<CountryDataModel> getCountriesList() {
        return getCountriesList(getCountriesStr());
    }

    protected List<CountryDataModel> getCountriesList(String countriesStr) {
        Type listType = new TypeToken<List<CountryDataModel>>() {
        }.getType();
        JsonReader reader = new JsonReader(new StringReader(countriesStr));
        reader.setLenient(true);
        return new Gson().fromJson(reader, listType);
    }

    public String getCountriesStr() {
        String language = getCurrentLocale(context).getLanguage().toLowerCase();
        return getCountriesStr(language);
    }

    public String getCountriesStr(String language) {
        final String Countries_PREFIX = "countries_";
        String countriesStr = AssetUtils.loadJSONFromAsset(context, Countries_PREFIX + language + ".json");
        if (countriesStr == null)//return the english version in case no Locale one found
            return AssetUtils.loadJSONFromAsset(context, Countries_PREFIX + "en" + ".json");
        return countriesStr;
    }
}
