package com.dynamsoft.concurrentInstanceTest.zz3rdPart;

import com.dynamsoft.dbr.BarcodeReader;
import com.dynamsoft.dbr.TextResult;

public class DynamsoftBarcodeWrapper {

    public static Exception initException = null;

    // For the sake of generality, spring's dependency injection mechanism is not used.
    static {
        // This is a trial license, which will be invalid in 3 days since 2023-02-15.
        String license = "DLS2eyJoYW5kc2hha2VDb2RlIjoiMjE5ODYyLTEwMTY4NTMxOCIsIm1haW5TZXJ2ZXJVUkwiOiJodHRwczovL21sdHMuZHluYW1zb2Z0LmNvbS8iLCJvcmdhbml6YXRpb25JRCI6IjIxOTg2MiIsInN0YW5kYnlTZXJ2ZXJVUkwiOiJodHRwczovL3NsdHMuZHluYW1zb2Z0LmNvbS8iLCJjaGVja0NvZGUiOi0zNjExMzAwMjl9";
        int countForThisDevice = 1;
        int countForThisProcess = 1;
        try {
            BarcodeReader.setMaxConcurrentInstanceCount(countForThisDevice, countForThisProcess);
            BarcodeReader.initLicense(license);
        } catch (Exception ex) {
            ex.printStackTrace();
            initException = ex;
        }
    }

    public static TextResult[] decode(byte[] bytes) throws Exception {
        if(null != initException){ throw  initException; }
        var reader = BarcodeReader.getInstance();
        var results = reader.decodeFileInMemory(bytes, "");
        reader.recycle();
        return results;
    }
}
