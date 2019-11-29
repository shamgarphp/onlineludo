package com.smart.ludoclassic;

import android.media.AudioManager;
import android.media.SoundPool;
import java.util.HashMap;

public class SoundUtil {
    private HashMap<Integer, Integer> mSoundMap;
    private SoundPool mSoundPool;
    AudioManager mgr;

    public SoundUtil(SoundPool mSoundPool, HashMap<Integer, Integer> mSoundMap, AudioManager mgr) {
        this.mSoundPool = mSoundPool;
        this.mSoundMap = mSoundMap;
        this.mgr = mgr;
    }

    public void playSound(int sound, int loop) {
        float volume = ((float) this.mgr.getStreamVolume(3)) / ((float) this.mgr.getStreamMaxVolume(3));
        if (this.mSoundPool != null) {
            this.mSoundPool.play((Integer) this.mSoundMap.get(sound), volume, volume, 1, 0, 1.0f);
        }
    }
}
