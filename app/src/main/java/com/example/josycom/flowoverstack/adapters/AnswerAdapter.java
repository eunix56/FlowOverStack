package com.example.josycom.flowoverstack.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.josycom.flowoverstack.R;
import com.example.josycom.flowoverstack.model.Answer;
import com.example.josycom.flowoverstack.util.DateUtil;

import org.jsoup.Jsoup;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {

    private List<Answer> mAnswers;
    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        if (mAnswers != null){
            Answer currentAnswer = mAnswers.get(position);
            holder.bind(currentAnswer);
        }
    }

    public void setAnswers(List<Answer> answers) {
        mAnswers = answers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAnswers != null){
            return mAnswers.size();
        } else {
            return 0;
        }
    }

    static class AnswerViewHolder extends RecyclerView.ViewHolder {
        TextView answerBody, answerDate, answerName, answerScore;

        AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            answerBody = itemView.findViewById(R.id.tv_answer_body_item);
            answerDate = itemView.findViewById(R.id.tv_date_answer_item);
            answerName = itemView.findViewById(R.id.tv_name_answer_item);
            answerScore = itemView.findViewById(R.id.tv_votes_item);
        }

        void bind(Answer answer){
            answerScore.setText(String.valueOf(answer.getScore()));
            answerName.setText(answer.getOwner().getDisplayName());
            answerDate.setText(DateUtil.toNormalDate(answer.getCreationDate()));
            answerBody.setText(Jsoup.parse(answer.getBody()).text());
        }
    }
}