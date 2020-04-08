package bankStructure;

public class Transaction {
    private final String fromId;
    private final String toId;
    private final long amount;

    public Transaction(String fromId, String toId, long amount) throws IllegalArgumentException {
        if (fromId == null)
        {
            throw new IllegalArgumentException("Invalid account fromId.");
        }
        if (toId == null)
        {
            throw new IllegalArgumentException("Invalid account toId");
        }
        if (amount < 0)
        {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.fromId = fromId;
        this.toId = toId;
        this.amount = amount;
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

    public long getAmount() {
        return amount;
    }
}
