package com.tts.mapsapp.deserialization;

public class RandomCoords {
    public double generateRandomCoords()
    {
        var num = Math.random()*180;
        var posorneg = Math.floor(Math.random());
        if (posorneg == 0)
        {
            num = num * -1;
        }
        return num;
    }
}
