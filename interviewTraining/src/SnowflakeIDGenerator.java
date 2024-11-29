public class SnowflakeIDGenerator {
    private static final long EPOCH = 1609459200000L;
    private static final long MACHINE_ID_BITS = 10; // Bits reservados para ID da máquina.
    private static final long SEQUENCE_BITS = 12; // Bits reservados para sequência.

    private static final long MAX_MACHINE_ID = (1L << MACHINE_ID_BITS) - 1;
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private final long machineId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIDGenerator(long machineId) {
        if (machineId < 0 || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException("Machine ID Must be between 0 and " + MAX_MACHINE_ID);
        }
        this.machineId = machineId;
    }

    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Not possible to generate ID.");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // Aguarde até o próximo milissegundo.
                while (currentTimestamp <= lastTimestamp) {
                    currentTimestamp = System.currentTimeMillis();
                }
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // Construção do ID único.
        return ((currentTimestamp - EPOCH) << (MACHINE_ID_BITS + SEQUENCE_BITS))
                | (machineId << SEQUENCE_BITS)
                | sequence;
    }

    public static void main(String[] args) {
        SnowflakeIDGenerator generator = new SnowflakeIDGenerator(1); // ID da máquina = 1

        for (int i = 0; i < 10; i++) {
            System.out.println(generator.nextId());
        }
    }
}
