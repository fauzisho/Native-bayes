package android.uzi.nativebayes.bayes;

/**
 * Created by uzi on 09/06/17.
 * Email : fauzisholichin@gmail.com
 */

interface IFeatureProbability<T, K> {
    public float featureProbability(T feature, K category);
}
