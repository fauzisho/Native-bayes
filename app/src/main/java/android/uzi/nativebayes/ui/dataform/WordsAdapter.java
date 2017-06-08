package android.uzi.nativebayes.ui.dataform;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.uzi.nativebayes.R;
import android.uzi.nativebayes.data.model.Word;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by uzi on 09/06/17.
 * Email : fauzisholichin@gmail.com
 */

public class WordsAdapter extends ArrayAdapter<Word> {
    private WordsAdapterListener mListener;

    public WordsAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0, objects);
    }


    public void setListener(WordsAdapterListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_word, parent, false);

        ViewHolder holder = new ViewHolder(convertView);
        holder.bind(getItem(position), position);

        return convertView;
    }


    public interface WordsAdapterListener {
        void onLongClick(Word item);

        void onClick(Word item);
    }

    private class ViewHolder {
        private final TextView tvItemNo;
        private final TextView tvFeature;
        private final TextView tvCategory;
        private final LinearLayout llWord;

        public ViewHolder(View v) {
            tvItemNo = (TextView) v.findViewById(R.id.tvItemNo);
            tvFeature = (TextView) v.findViewById(R.id.tvFeature);
            tvCategory = (TextView) v.findViewById(R.id.tvCategory);
            llWord = (LinearLayout) v.findViewById(R.id.llWord);
        }

        public void bind(final Word item, int position) {
            String no = String.valueOf(position);

            tvItemNo.setText(no);
            tvFeature.setText(item.getFeature());
            tvCategory.setText(item.getCategory());

            llWord.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onLongClick(item);
                    return true;
                }
            });
            llWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(item);
                }
            });

        }
    }

}
