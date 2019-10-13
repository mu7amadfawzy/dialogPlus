package com.dialog.plus.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.dialog.plus.R;

/**
 * Created by Fawzy on 23,September,2019
 * ma7madfawzy@gmail.com
 **/
public class FlagsUtil {
    private static final String FLAG_PREFIX = "flag_";

    public static int getFlagByLanguage(String language) {
        switch (language) {
            //this should be sorted based on country name code.
            case "ad": //andorra
                return R.mipmap.flag_ad;
            case "ae": //united arab emirates
                return R.mipmap.flag_ae;
            case "af": //afghanistan
                return R.mipmap.flag_af;
            case "ag": //antigua & barbuda
                return R.mipmap.flag_ag;
            case "ai": //anguilla // Caribbean Islands
                return R.mipmap.flag_ai;
            case "al": //albania
                return R.mipmap.flag_al;
            case "am": //armenia
                return R.mipmap.flag_am;
            case "ao": //angola
                return R.mipmap.flag_ao;
            case "aq": //antarctica // custom
                return R.mipmap.flag_aq;
            case "ar": //argentina
                return R.mipmap.flag_ar;
            case "as": //American Samoa
                return R.mipmap.flag_as;
            case "at": //austria
                return R.mipmap.flag_at;
            case "au": //australia
                return R.mipmap.flag_au;
            case "aw": //aruba
                return R.mipmap.flag_aw;
            case "ax": //alan islands
                return R.mipmap.flag_ax;
            case "az": //azerbaijan
                return R.mipmap.flag_az;
            case "ba": //bosnia and herzegovina
                return R.mipmap.flag_ba;
            case "bb": //barbados
                return R.mipmap.flag_bb;
            case "bd": //bangladesh
                return R.mipmap.flag_bd;
            case "be": //belgium
                return R.mipmap.flag_be;
            case "bf": //burkina faso
                return R.mipmap.flag_bf;
            case "bg": //bulgaria
                return R.mipmap.flag_bg;
            case "bh": //bahrain
                return R.mipmap.flag_bh;
            case "bi": //burundi
                return R.mipmap.flag_bi;
            case "bj": //benin
                return R.mipmap.flag_bj;
            case "bl": //saint barthélemy
                return R.mipmap.flag_bl;// custom
            case "bm": //bermuda
                return R.mipmap.flag_bm;
            case "bn": //brunei darussalam // custom
                return R.mipmap.flag_bn;
            case "bo": //bolivia, plurinational state of
                return R.mipmap.flag_bo;
            case "br": //brazil
                return R.mipmap.flag_br;
            case "bs": //bahamas
                return R.mipmap.flag_bs;
            case "bt": //bhutan
                return R.mipmap.flag_bt;
            case "bw": //botswana
                return R.mipmap.flag_bw;
            case "by": //belarus
                return R.mipmap.flag_by;
            case "bz": //belize
                return R.mipmap.flag_bz;
            case "ca": //canada
                return R.mipmap.flag_ca;
            case "cc": //cocos (keeling) islands
                return R.mipmap.flag_cc;// custom
            case "cd": //congo, the democratic republic of the
                return R.mipmap.flag_cd;
            case "cf": //central african republic
                return R.mipmap.flag_cf;
            case "cg": //congo
                return R.mipmap.flag_cg;
            case "ch": //switzerland
                return R.mipmap.flag_ch;
            case "ci": //côte d\'ivoire
                return R.mipmap.flag_ci;
            case "ck": //cook islands
                return R.mipmap.flag_ck;
            case "cl": //chile
                return R.mipmap.flag_cl;
            case "cm": //cameroon
                return R.mipmap.flag_cm;
            case "cn": //china
                return R.mipmap.flag_cn;
            case "co": //colombia
                return R.mipmap.flag_co;
            case "cr": //costa rica
                return R.mipmap.flag_cr;
            case "cu": //cuba
                return R.mipmap.flag_cu;
            case "cv": //cape verde
                return R.mipmap.flag_cv;
            case "cw": //curaçao
                return R.mipmap.flag_cw;
            case "cx": //christmas island
                return R.mipmap.flag_cx;
            case "cy": //cyprus
                return R.mipmap.flag_cy;
            case "cz": //czech republic
                return R.mipmap.flag_cz;
            case "de": //germany
                return R.mipmap.flag_de;
            case "dj": //djibouti
                return R.mipmap.flag_dj;
            case "dk": //denmark
                return R.mipmap.flag_dk;
            case "dm": //dominica
                return R.mipmap.flag_dm;
            case "do": //dominican republic
                return R.mipmap.flag_do;
            case "dz": //algeria
                return R.mipmap.flag_dz;
            case "ec": //ecuador
                return R.mipmap.flag_ec;
            case "ee": //estonia
                return R.mipmap.flag_ee;
            case "eg": //egypt
                return R.mipmap.flag_eg;
            case "er": //eritrea
                return R.mipmap.flag_er;
            case "es": //spain
                return R.mipmap.flag_es;
            case "et": //ethiopia
                return R.mipmap.flag_et;
            case "fi": //finland
                return R.mipmap.flag_fi;
            case "fj": //fiji
                return R.mipmap.flag_fj;
            case "fk": //falkland islands (malvinas)
                return R.mipmap.flag_fk;
            case "fm": //micronesia, federated states of
                return R.mipmap.flag_fm;
            case "fo": //faroe islands
                return R.mipmap.flag_fo;
            case "fr": //france
                return R.mipmap.flag_fr;
            case "ga": //gabon
                return R.mipmap.flag_ga;
            case "gb": //united kingdom
                return R.mipmap.flag_gb;
            case "gd": //grenada
                return R.mipmap.flag_gd;
            case "ge": //georgia
                return R.mipmap.flag_ge;
            case "gf": //guyane
                return R.mipmap.flag_gf;
            case "gg": //Guernsey
                return R.mipmap.flag_gg;
            case "gh": //ghana
                return R.mipmap.flag_gh;
            case "gi": //gibraltar
                return R.mipmap.flag_gi;
            case "gl": //greenland
                return R.mipmap.flag_gl;
            case "gm": //gambia
                return R.mipmap.flag_gm;
            case "gn": //guinea
                return R.mipmap.flag_gn;
            case "gp": //guadeloupe
                return R.mipmap.flag_gp;
            case "gq": //equatorial guinea
                return R.mipmap.flag_gq;
            case "gr": //greece
                return R.mipmap.flag_gr;
            case "gt": //guatemala
                return R.mipmap.flag_gt;
            case "gu": //Guam
                return R.mipmap.flag_gu;
            case "gw": //guinea-bissau
                return R.mipmap.flag_gw;
            case "gy": //guyana
                return R.mipmap.flag_gy;
            case "hk": //hong kong
                return R.mipmap.flag_hk;
            case "hn": //honduras
                return R.mipmap.flag_hn;
            case "hr": //croatia
                return R.mipmap.flag_hr;
            case "ht": //haiti
                return R.mipmap.flag_ht;
            case "hu": //hungary
                return R.mipmap.flag_hu;
            case "id": //indonesia
                return R.mipmap.flag_id;
            case "ie": //ireland
                return R.mipmap.flag_ie;
            case "il": //israel
                return R.mipmap.flag_il;
            case "im": //isle of man
                return R.mipmap.flag_im; // custom
            case "is": //Iceland
                return R.mipmap.flag_is;
            case "in": //india
                return R.mipmap.flag_in;
            case "io": //British indian ocean territory
                return R.mipmap.flag_io;
            case "iq": //iraq
                return R.mipmap.flag_iq;
            case "ir": //iran, islamic republic of
                return R.mipmap.flag_ir;
            case "it": //italy
                return R.mipmap.flag_it;
            case "je": //Jersey
                return R.mipmap.flag_je;
            case "jm": //jamaica
                return R.mipmap.flag_jm;
            case "jo": //jordan
                return R.mipmap.flag_jo;
            case "jp": //japan
                return R.mipmap.flag_jp;
            case "ke": //kenya
                return R.mipmap.flag_ke;
            case "kg": //kyrgyzstan
                return R.mipmap.flag_kg;
            case "kh": //cambodia
                return R.mipmap.flag_kh;
            case "ki": //kiribati
                return R.mipmap.flag_ki;
            case "km": //comoros
                return R.mipmap.flag_km;
            case "kn": //st kitts & nevis
                return R.mipmap.flag_kn;
            case "kp": //north korea
                return R.mipmap.flag_kp;
            case "kr": //south korea
                return R.mipmap.flag_kr;
            case "kw": //kuwait
                return R.mipmap.flag_kw;
            case "ky": //Cayman_Islands
                return R.mipmap.flag_ky;
            case "kz": //kazakhstan
                return R.mipmap.flag_kz;
            case "la": //lao people\'s democratic republic
                return R.mipmap.flag_la;
            case "lb": //lebanon
                return R.mipmap.flag_lb;
            case "lc": //st lucia
                return R.mipmap.flag_lc;
            case "li": //liechtenstein
                return R.mipmap.flag_li;
            case "lk": //sri lanka
                return R.mipmap.flag_lk;
            case "lr": //liberia
                return R.mipmap.flag_lr;
            case "ls": //lesotho
                return R.mipmap.flag_ls;
            case "lt": //lithuania
                return R.mipmap.flag_lt;
            case "lu": //luxembourg
                return R.mipmap.flag_lu;
            case "lv": //latvia
                return R.mipmap.flag_lv;
            case "ly": //libya
                return R.mipmap.flag_ly;
            case "ma": //morocco
                return R.mipmap.flag_ma;
            case "mc": //monaco
                return R.mipmap.flag_mc;
            case "md": //moldova, republic of
                return R.mipmap.flag_md;
            case "me": //montenegro
                return R.mipmap.flag_me;// custom
            case "mf":
                return R.mipmap.flag_mf;
            case "mg": //madagascar
                return R.mipmap.flag_mg;
            case "mh": //marshall islands
                return R.mipmap.flag_mh;
            case "mk": //macedonia, the former yugoslav republic of
                return R.mipmap.flag_mk;
            case "ml": //mali
                return R.mipmap.flag_ml;
            case "mm": //myanmar
                return R.mipmap.flag_mm;
            case "mn": //mongolia
                return R.mipmap.flag_mn;
            case "mo": //macao
                return R.mipmap.flag_mo;
            case "mp": // Northern mariana islands
                return R.mipmap.flag_mp;
            case "mq": //martinique
                return R.mipmap.flag_yt;
            case "mr": //mauritania
                return R.mipmap.flag_mr;
            case "ms": //montserrat
                return R.mipmap.flag_ms;
            case "mt": //malta
                return R.mipmap.flag_mt;
            case "mu": //mauritius
                return R.mipmap.flag_mu;
            case "mv": //maldives
                return R.mipmap.flag_mv;
            case "mw": //malawi
                return R.mipmap.flag_mw;
            case "mx": //mexico
                return R.mipmap.flag_mx;
            case "my": //malaysia
                return R.mipmap.flag_my;
            case "mz": //mozambique
                return R.mipmap.flag_mz;
            case "na": //namibia
                return R.mipmap.flag_na;
            case "nc": //new caledonia
                return R.mipmap.flag_nc;// custom
            case "ne": //niger
                return R.mipmap.flag_ne;
            case "nf": //Norfolk
                return R.mipmap.flag_nf;
            case "ng": //nigeria
                return R.mipmap.flag_ng;
            case "ni": //nicaragua
                return R.mipmap.flag_ni;
            case "nl": //netherlands
                return R.mipmap.flag_nl;
            case "no": //norway
                return R.mipmap.flag_no;
            case "np": //nepal
                return R.mipmap.flag_np;
            case "nr": //nauru
                return R.mipmap.flag_nr;
            case "nu": //niue
                return R.mipmap.flag_nu;
            case "nz": //new zealand
                return R.mipmap.flag_nz;
            case "om": //oman
                return R.mipmap.flag_om;
            case "pa": //panama
                return R.mipmap.flag_pa;
            case "pe": //peru
                return R.mipmap.flag_pe;
            case "pf": //french polynesia
                return R.mipmap.flag_pf;
            case "pg": //papua new guinea
                return R.mipmap.flag_pg;
            case "ph": //philippines
                return R.mipmap.flag_ph;
            case "pk": //pakistan
                return R.mipmap.flag_pk;
            case "pl": //poland
                return R.mipmap.flag_pl;
            case "pm": //saint pierre and miquelon
                return R.mipmap.flag_pm;
            case "pn": //pitcairn
                return R.mipmap.flag_pn;
            case "pr": //puerto rico
                return R.mipmap.flag_pr;
            case "ps": //palestine
                return R.mipmap.flag_ps;
            case "pt": //portugal
                return R.mipmap.flag_pt;
            case "pw": //palau
                return R.mipmap.flag_pw;
            case "py": //paraguay
                return R.mipmap.flag_py;
            case "qa": //qatar
                return R.mipmap.flag_qa;
            case "re": //la reunion
                return R.mipmap.flag_yt; // no exact flag found
            case "ro": //romania
                return R.mipmap.flag_ro;
            case "rs": //serbia
                return R.mipmap.flag_rs; // custom
            case "ru": //russian federation
                return R.mipmap.flag_ru;
            case "rw": //rwanda
                return R.mipmap.flag_rw;
            case "sa": //saudi arabia
                return R.mipmap.flag_sa;
            case "sb": //solomon islands
                return R.mipmap.flag_sb;
            case "sc": //seychelles
                return R.mipmap.flag_sc;
            case "sd": //sudan
                return R.mipmap.flag_sd;
            case "se": //sweden
                return R.mipmap.flag_se;
            case "sg": //singapore
                return R.mipmap.flag_sg;
            case "sh": //saint helena, ascension and tristan da cunha
                return R.mipmap.flag_sh; // custom
            case "si": //slovenia
                return R.mipmap.flag_si;
            case "sk": //slovakia
                return R.mipmap.flag_sk;
            case "sl": //sierra leone
                return R.mipmap.flag_sl;
            case "sm": //san marino
                return R.mipmap.flag_sm;
            case "sn": //senegal
                return R.mipmap.flag_sn;
            case "so": //somalia
                return R.mipmap.flag_so;
            case "sr": //suriname
                return R.mipmap.flag_sr;
            case "ss": //south sudan
                return R.mipmap.flag_ss;
            case "st": //sao tome and principe
                return R.mipmap.flag_st;
            case "sv": //el salvador
                return R.mipmap.flag_sv;
            case "sx": //sint maarten
                return R.mipmap.flag_sx;
            case "sy": //syrian arab republic
                return R.mipmap.flag_sy;
            case "sz": //swaziland
                return R.mipmap.flag_sz;
            case "tc": //turks & caicos islands
                return R.mipmap.flag_tc;
            case "td": //chad
                return R.mipmap.flag_td;
            case "tg": //togo
                return R.mipmap.flag_tg;
            case "th": //thailand
                return R.mipmap.flag_th;
            case "tj": //tajikistan
                return R.mipmap.flag_tj;
            case "tk": //tokelau
                return R.mipmap.flag_tk; // custom
            case "tl": //timor-leste
                return R.mipmap.flag_tl;
            case "tm": //turkmenistan
                return R.mipmap.flag_tm;
            case "tn": //tunisia
                return R.mipmap.flag_tn;
            case "to": //tonga
                return R.mipmap.flag_to;
            case "tr": //turkey
                return R.mipmap.flag_tr;
            case "tt": //trinidad & tobago
                return R.mipmap.flag_tt;
            case "tv": //tuvalu
                return R.mipmap.flag_tv;
            case "tw": //taiwan, province of china
                return R.mipmap.flag_tw;
            case "tz": //tanzania, united republic of
                return R.mipmap.flag_tz;
            case "ua": //ukraine
                return R.mipmap.flag_ua;
            case "ug": //uganda
                return R.mipmap.flag_ug;
            case "us": //united states
                return R.mipmap.flag_us;
            case "uy": //uruguay
                return R.mipmap.flag_uy;
            case "uz": //uzbekistan
                return R.mipmap.flag_uz;
            case "va": //holy see (vatican city state)
                return R.mipmap.flag_va;
            case "vc": //st vincent & the grenadines
                return R.mipmap.flag_vc;
            case "ve": //venezuela, bolivarian republic of
                return R.mipmap.flag_ve;
            case "vg": //british virgin islands
                return R.mipmap.flag_vg;
            case "vi": //us virgin islands
                return R.mipmap.flag_vi;
            case "vn": //vietnam
                return R.mipmap.flag_vn;
            case "vu": //vanuatu
                return R.mipmap.flag_vu;
            case "wf": //wallis and futuna
                return R.mipmap.flag_wf;
            case "ws": //samoa
                return R.mipmap.flag_ws;
            case "xk": //kosovo
                return R.mipmap.flag_xk;
            case "ye": //yemen
                return R.mipmap.flag_ye;
            case "yt": //mayotte
                return R.mipmap.flag_yt; // no exact flag found
            case "za": //south africa
                return R.mipmap.flag_za;
            case "zm": //zambia
                return R.mipmap.flag_zm;
            case "zw": //zimbabwe
                return R.mipmap.flag_zw;
            default:
                return R.mipmap.flag_transparent;
        }
    }

    public static Drawable getFlagByLanguage(Context context, String language) {
        final int resourceId = context.getResources().getIdentifier(FLAG_PREFIX + language, "mipmap",
                context.getPackageName());
        try {
            return context.getResources().getDrawable(resourceId);
        } catch (Exception e) {
            Log.e("dialogPlus", "no drawable with name " + FLAG_PREFIX + language);
            return null;
        }
    }
}
