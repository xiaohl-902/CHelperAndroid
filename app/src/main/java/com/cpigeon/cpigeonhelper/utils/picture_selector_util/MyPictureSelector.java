package com.cpigeon.cpigeonhelper.utils.picture_selector_util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.luck.picture.lib.PictureExternalPreviewActivity;
import com.luck.picture.lib.PictureVideoPlayActivity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DoubleUtils;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/3.
 */

public final class MyPictureSelector {
    private final WeakReference<Activity> mActivity;
    private final WeakReference<Fragment> mFragment;

    private MyPictureSelector(Activity activity) {
        this(activity, (Fragment)null);
    }

    private MyPictureSelector(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private MyPictureSelector(Activity activity, Fragment fragment) {
        this.mActivity = new WeakReference(activity);
        this.mFragment = new WeakReference(fragment);
    }

    public static MyPictureSelector create(Activity activity) {
        return new MyPictureSelector(activity);
    }

    public static MyPictureSelector create(Fragment fragment) {
        return new MyPictureSelector(fragment);
    }

//    public PictureSelectionModel openGallery(int mimeType) {
//        return new PictureSelectionModel(this, mimeType);
//    }
//
//    public PictureSelectionModel openCamera(int mimeType) {
//        return new PictureSelectionModel(this, mimeType, true);
//    }

    public static List<LocalMedia> obtainMultipleResult(Intent data) {
        ArrayList result = new ArrayList();
        if(data != null) {
            Object result1 = (List)data.getSerializableExtra("extra_result_media");
            if(result1 == null) {
                result1 = new ArrayList();
            }

            return (List)result1;
        } else {
            return result;
        }
    }

    public static Intent putIntentResult(List<LocalMedia> data) {
        return (new Intent()).putExtra("extra_result_media", (Serializable)data);
    }

    public static List<LocalMedia> obtainSelectorList(Bundle bundle) {
        if(bundle != null) {
            List selectionMedias1 = (List)bundle.getSerializable("selectList");
            return selectionMedias1;
        } else {
            ArrayList selectionMedias = new ArrayList();
            return selectionMedias;
        }
    }

    public static void saveSelectorList(Bundle outState, List<LocalMedia> selectedImages) {
        outState.putSerializable("selectList", (Serializable)selectedImages);
    }

    public void externalPicturePreview(int position, List<LocalMedia> medias) {
        if(!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(this.getActivity(), PictureExternalPreviewActivity.class);
            intent.putExtra("previewSelectList", (Serializable)medias);
            intent.putExtra("position", position);
            this.getActivity().startActivity(intent);
            this.getActivity().overridePendingTransition(com.luck.picture.lib.R.anim.a5, 0);
        }

    }

    public void externalPicturePreview(int position, String directory_path, List<LocalMedia> medias) {
        if(!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(this.getActivity(), PictureExternalPreviewActivity.class);
            intent.putExtra("previewSelectList", (Serializable)medias);
            intent.putExtra("position", position);
            intent.putExtra("directory_path", directory_path);
            this.getActivity().startActivity(intent);
            this.getActivity().overridePendingTransition(com.luck.picture.lib.R.anim.a5, 0);
        }

    }

    public void externalPictureVideo(String path) {
        if(!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(this.getActivity(), PictureVideoPlayActivity.class);
            intent.putExtra("video_path", path);
            this.getActivity().startActivity(intent);
        }

    }

    @Nullable
    Activity getActivity() {
        return (Activity)this.mActivity.get();
    }

    @Nullable
    Fragment getFragment() {
        return this.mFragment != null?(Fragment)this.mFragment.get():null;
    }
}
