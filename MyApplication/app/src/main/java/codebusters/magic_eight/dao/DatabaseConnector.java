package codebusters.magic_eight.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by greg on 04/12/16.
 */

public class DatabaseConnector {

    private static final String DATABASE_NAME = "MagicEight";
    private static final String TABLE_NAME = "user";
    private SQLiteDatabase database;
    private DatabasesOpenHelper databasesOpenHelper;

    public DatabaseConnector(Context context) {
        databasesOpenHelper = new DatabasesOpenHelper(context, DATABASE_NAME, null, 0);
    }

    public void open() throws SQLException {
        database = databasesOpenHelper.getWritableDatabase();
    }

    public void insert(String name, String sign) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("sign", sign);

        open();
        database.insert(TABLE_NAME, null,cv);
        database.close();
    }

    public Cursor getUserById(int id) {
        return database.query(TABLE_NAME, null, "id=" + id, null, null, null, null);
    }

    public Cursor getUsers() {
        return database.query(TABLE_NAME, new String[] {"name", "sign"},null, null, null, null, null);
    }

    public String getSign(Calendar birthday) {

        // we need to compare only days and months
        birthday.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));

        //aquarius
        Calendar aquariusStart = Calendar.getInstance();
        aquariusStart.set(Calendar.MONTH, Calendar.JANUARY);
        aquariusStart.set(Calendar.DAY_OF_MONTH, 21);

        Calendar aquariusEnd = Calendar.getInstance();
        aquariusEnd.set(Calendar.MONTH, Calendar.FEBRUARY);
        aquariusEnd.set(Calendar.DAY_OF_MONTH, 19);

        //pisces
        Calendar piscesStart = Calendar.getInstance();
        piscesStart.set(Calendar.MONTH, Calendar.FEBRUARY);
        piscesStart.set(Calendar.DAY_OF_MONTH, 20);

        Calendar piscesEnd = Calendar.getInstance();
        piscesEnd.set(Calendar.MONTH, Calendar.MARCH);
        piscesEnd.set(Calendar.DAY_OF_MONTH, 21);

        //aries
        Calendar ariesStart = Calendar.getInstance();
        ariesStart.set(Calendar.MONTH, Calendar.MARCH);
        ariesStart.set(Calendar.DAY_OF_MONTH, 21);

        Calendar ariesEnd = Calendar.getInstance();
        ariesEnd.set(Calendar.MONTH, Calendar.APRIL);
        ariesEnd.set(Calendar.DAY_OF_MONTH, 21);

        //taurus
        Calendar taurusStart = Calendar.getInstance();
        taurusStart.set(Calendar.MONTH, Calendar.APRIL);
        taurusStart.set(Calendar.DAY_OF_MONTH, 21);

        Calendar taurusEnd = Calendar.getInstance();
        taurusEnd.set(Calendar.MONTH, Calendar.MAY);
        taurusEnd.set(Calendar.DAY_OF_MONTH, 22);

        //gemini
        Calendar geminiStart = Calendar.getInstance();
        geminiStart.set(Calendar.MONTH, Calendar.MAY);
        geminiStart.set(Calendar.DAY_OF_MONTH, 22);

        Calendar geminiEnd = Calendar.getInstance();
        geminiEnd.set(Calendar.MONTH, Calendar.JUNE);
        geminiEnd.set(Calendar.DAY_OF_MONTH, 22);

        //cancer
        Calendar cancerStart = Calendar.getInstance();
        cancerStart.set(Calendar.MONTH, Calendar.JUNE);
        cancerStart.set(Calendar.DAY_OF_MONTH, 22);

        Calendar cancerEnd = Calendar.getInstance();
        cancerEnd.set(Calendar.MONTH, Calendar.JULY);
        cancerEnd.set(Calendar.DAY_OF_MONTH, 23);

        //leo
        Calendar leoStart = Calendar.getInstance();
        leoStart.set(Calendar.MONTH, Calendar.JULY);
        leoStart.set(Calendar.DAY_OF_MONTH, 23);

        Calendar leoEnd = Calendar.getInstance();
        leoEnd.set(Calendar.MONTH, Calendar.AUGUST);
        leoEnd.set(Calendar.DAY_OF_MONTH, 23);

        //virgo
        Calendar virgoStart = Calendar.getInstance();
        virgoStart.set(Calendar.MONTH, Calendar.AUGUST);
        virgoStart.set(Calendar.DAY_OF_MONTH, 23);

        Calendar virgoEnd = Calendar.getInstance();
        virgoEnd.set(Calendar.MONTH, Calendar.SEPTEMBER);
        virgoEnd.set(Calendar.DAY_OF_MONTH, 24);

        //libra
        Calendar libraStart = Calendar.getInstance();
        libraStart.set(Calendar.MONTH, Calendar.SEPTEMBER);
        libraStart.set(Calendar.DAY_OF_MONTH, 24);

        Calendar libraEnd = Calendar.getInstance();
        libraEnd.set(Calendar.MONTH, Calendar.OCTOBER);
        libraEnd.set(Calendar.DAY_OF_MONTH, 24);

        //scorpio
        Calendar scorpioStart = Calendar.getInstance();
        scorpioStart.set(Calendar.MONTH, Calendar.OCTOBER);
        scorpioStart.set(Calendar.DAY_OF_MONTH, 24);

        Calendar scorpioEnd = Calendar.getInstance();
        scorpioEnd.set(Calendar.MONTH, Calendar.NOVEMBER);
        scorpioEnd.set(Calendar.DAY_OF_MONTH, 22);

        //sagittarius
        Calendar sagittariusStart = Calendar.getInstance();
        sagittariusStart.set(Calendar.MONTH, Calendar.NOVEMBER);
        sagittariusStart.set(Calendar.DAY_OF_MONTH, 22);

        Calendar sagittariusEnd = Calendar.getInstance();
        sagittariusEnd.set(Calendar.MONTH, Calendar.DECEMBER);
        sagittariusEnd.set(Calendar.DAY_OF_MONTH, 22);

        //capricorn
        Calendar capricornStart1 = Calendar.getInstance();
        capricornStart1.set(Calendar.MONTH, Calendar.DECEMBER);
        capricornStart1.set(Calendar.DAY_OF_MONTH, 22);

        Calendar capricornEnd1 = Calendar.getInstance();
        capricornEnd1.set(Calendar.MONTH, Calendar.DECEMBER);
        capricornEnd1.set(Calendar.DAY_OF_MONTH, 31);

        Calendar capricornStart2 = Calendar.getInstance();
        capricornStart2.set(Calendar.MONTH, Calendar.JANUARY);
        capricornStart2.set(Calendar.DAY_OF_MONTH, 0);

        Calendar capricornEnd2 = Calendar.getInstance();
        capricornEnd2.set(Calendar.MONTH, Calendar.JANUARY);
        capricornEnd2.set(Calendar.DAY_OF_MONTH, 21);

        if (birthday.equals(ariesStart) || birthday.equals(ariesEnd) ||
                (birthday.after(ariesStart) && birthday.before(ariesEnd))) {
            return "Aries";
        } else if (birthday.equals(taurusStart) || birthday.equals(taurusEnd) ||
                (birthday.after(taurusStart) && birthday.before(taurusEnd))) {
            return "Taurus";
        } else if (birthday.equals(geminiStart) || birthday.equals(geminiEnd) ||
                (birthday.after(geminiStart) && birthday.before(geminiEnd))) {
            return "Gemini";
        } else if (birthday.equals(cancerStart) || birthday.equals(cancerEnd) ||
                (birthday.after(cancerStart) && birthday.before(cancerEnd))) {
            return "Cancer";
        } else if (birthday.equals(leoStart) || birthday.equals(leoEnd) ||
                (birthday.after(leoStart) && birthday.before(leoEnd))) {
            return "Leo";
        } else if (birthday.equals(virgoStart) || birthday.equals(virgoEnd) ||
                (birthday.after(virgoStart) && birthday.before(virgoEnd))) {
            return "Virgo";
        } else if (birthday.equals(libraStart) || birthday.equals(libraEnd) ||
                (birthday.after(libraStart) && birthday.before(libraEnd))) {
            return "Libra";
        } else if (birthday.equals(scorpioStart) || birthday.equals(scorpioEnd) ||
                (birthday.after(scorpioStart) && birthday.before(scorpioEnd))) {
            return "Scorpio";
        } else if (birthday.equals(sagittariusStart) || birthday.equals(sagittariusEnd) ||
                (birthday.after(sagittariusStart) && birthday.before(sagittariusEnd))) {
            return "Sagittarius";
        } else if (birthday.equals(capricornStart1) || birthday.equals(capricornEnd1) ||
                (birthday.after(capricornStart1) && birthday.before(capricornEnd1))) {
            return "Capricorn";
        } else if (birthday.equals(capricornStart2) || birthday.equals(capricornEnd2) ||
                (birthday.after(capricornStart2) && birthday.before(capricornEnd2))) {
            return "Capricorn";
        } else if (birthday.equals(aquariusStart) || birthday.equals(aquariusEnd) ||
                (birthday.after(aquariusStart) && birthday.before(aquariusEnd))) {
            return "Aquarius";
        } else if (birthday.equals(piscesStart) || birthday.equals(piscesEnd) ||
                (birthday.after(piscesStart) && birthday.before(piscesEnd))) {
            return "Pisces";
        }

        return "No sign!";
    }

}
