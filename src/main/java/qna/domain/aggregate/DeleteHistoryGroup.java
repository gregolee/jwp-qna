package qna.domain.aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import qna.domain.entity.DeleteHistory;

public class DeleteHistoryGroup {

	private List<DeleteHistory> deleteHistories;

	public DeleteHistoryGroup(List<DeleteHistory> deleteHistories) {
		if (Objects.isNull(deleteHistories)) {
			this.deleteHistories = new ArrayList<>();
			return;
		}
		this.deleteHistories = deleteHistories;
	}

	public List<DeleteHistory> deleteHistories() {
		return deleteHistories;
	}

	public void add(DeleteHistory deleteHistory) {
		deleteHistories.add(deleteHistory);
	}

	public void addAll(DeleteHistoryGroup deleteHistoryGroup) {
		this.deleteHistories.addAll(deleteHistoryGroup.deleteHistories);
	}

	public int size() {
		return deleteHistories.size();
	}
}
