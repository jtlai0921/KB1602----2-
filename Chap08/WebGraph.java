
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;


import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityIndex;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import com.sleepycat.persist.StoreConfig;

public class WebGraph {
	// �X��
	private PrimaryIndex<String, Link> outLinkIndex;
	// �J��
	private SecondaryIndex<String, String, Link> inLinkIndex;

	private EntityStore store;

	/**
	 * �غc���
	 */
	public WebGraph(String dbDir) throws DatabaseException {
		File envDir = new File(dbDir);
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(false);
		envConfig.setAllowCreate(true);
		Environment env = new Environment(envDir, envConfig);

		StoreConfig storeConfig = new StoreConfig();
		storeConfig.setAllowCreate(true);
		storeConfig.setTransactional(false);

		store = new EntityStore(env, "classDb", storeConfig);
		outLinkIndex = store.getPrimaryIndex(String.class, Link.class);
		inLinkIndex = store.getSecondaryIndex(outLinkIndex, String.class,
				"toURL");
	}

	/**
	 * �غcWeb�ϡA�q�ɮפ�Ū�J�C�C�@�欰�@�ӹ������Y�A�Ҧp http://url1.com -> http://url2.com 1.0 ���ܹ���챵
	 * http://url1.com�Ҫ��ܪ������W���A���@�ӶW�챵http://url2.com �åB�L�̤������v����1.0
	 */
	public void load(File file) throws IOException, FileNotFoundException,
			DatabaseException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			int index1 = line.indexOf("->");
			if (index1 == -1) {
				continue;
			} else {
				String url1 = line.substring(0, index1).trim();
				String url2 = line.substring(index1 + 2).trim();
				// Double strength = new Double(1.0);
				index1 = url2.indexOf(" ");
				if (index1 != -1)
					try {
						// strength = new
						// Double(url2.substring(index1+1).trim());
						url2 = url2.substring(0, index1).trim();
					} catch (Exception e) {
					}
				addLink(url1, url2);
			}
		}
	}

	/**
	 * �[�J�`�I�������������Y�A�p�G�`�I���s�b�A�N�إߡA�p�G�w�g�s�b������ �t�A�N��s�v��
	 * 
	 */
	public void addLink(String fromLink, String toLink)
			throws DatabaseException {
		Link outLinks = new Link();
		outLinks.fromURL = fromLink;
		outLinks.toURL = new HashSet<String>();
		outLinks.toURL.add(toLink);
		boolean inserted = outLinkIndex.putNoOverwrite(outLinks);
		if (!inserted) {
			outLinks = outLinkIndex.get(fromLink);
			outLinks.toURL.add(toLink);
			// System.out.println("outLinks : "+ outLinks.fromURL + "
			// outLinks.toURL:"+outLinks.toURL.size());
			// System.out.println(fromLink+" : "+ toLink);
			outLinkIndex.put(outLinks);
		}
	}

	// �ھڨ�w��URL�A��o���V�L���J���챵
	public String[] inLinks(String URL) throws DatabaseException {
		EntityIndex<String, Link> subIndex = inLinkIndex.subIndex(URL);
		// System.out.println(subIndex.count());
		String[] linkList = new String[(int) subIndex.count()];
		int i = 0;
		EntityCursor<Link> cursor = subIndex.entities();
		try {
			for (Link entity : cursor) {
				linkList[i++] = entity.fromURL;
				// System.out.println(entity.fromURL);
			}
		} finally {
			cursor.close();
		}
		return linkList;
	}
}