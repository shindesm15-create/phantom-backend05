@Service
public class ChatService {

    private final CatmRepository catmRepo;

    public ChatService(CatmRepository catmRepo) {
        this.catmRepo = catmRepo;
    }

    public Catm sendMessage(Catm msg) {

        msg.setTimestamp(System.currentTimeMillis());
        msg.setStatus("sent");

        return catmRepo.save(msg);
    }

    public List<Catm> getChat(String user1, String user2) {
        return catmRepo.findByFromAndToOrToAndFrom(user1, user2, user1, user2);
    }

    public void markSeen(String from, String to) {

        List<Catm> msgs = catmRepo.findByFromAndTo(from, to);

        for (Catm m : msgs) {
            m.setStatus("seen");
            catmRepo.save(m);
        }
    }
}
