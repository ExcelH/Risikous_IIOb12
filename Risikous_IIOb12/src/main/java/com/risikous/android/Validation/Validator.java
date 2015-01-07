package com.risikous.android.Validation;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Excel on 07.01.2015.
 */
public class Validator {

    public Validator(String s, File file, int max) throws ParseException, IOException {
        ValidatorCollection vc = new ValidatorCollection();

        try {
            if (s == "char") vc.validateChar(s);
            if (s == "required") vc.validateRequired(s);
            if (s == "count") vc.validateCountChar(s, max);
            if (s == "date") vc.validateDate(s);
            if (s == "time") vc.validateTime(s);
            if (s == "size") vc.validateFileSize(file, max);
            if (s == "encode") vc.validateFileEncode(file, s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
