package com.smart.ludoclassic;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import java.util.HashMap;

public class StaticVariables {

    public static boolean loaded;
    public static SoundUtil soundUtil;

    static class C04051 implements OnLoadCompleteListener {
        C04051() {
        }

        public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
            StaticVariables.loaded = true;
        }
    }

    public static void initSoundPool(Context context) {
        SoundPool mSoundPool = new SoundPool(2, 3, 100);
        HashMap<Integer, Integer> mSoundMap = new HashMap();
        AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mSoundPool.setOnLoadCompleteListener(new C04051());
        if (mSoundPool != null) {
            mSoundMap.put(1, mSoundPool.load(context, R.raw.tic_1, 1));
            mSoundMap.put(2, mSoundPool.load(context, R.raw.toc_1, 1));
            mSoundMap.put(3, mSoundPool.load(context, R.raw.drop_1, 1));
            mSoundMap.put(4, mSoundPool.load(context, R.raw.ladup, 1));
            mSoundMap.put(5, mSoundPool.load(context, R.raw.success_1, 1));
        }
        soundUtil = new SoundUtil(mSoundPool, mSoundMap, mgr);
    }
}
