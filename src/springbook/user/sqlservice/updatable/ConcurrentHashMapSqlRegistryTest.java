package springbook.user.sqlservice.updatable;

public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
	@Override
	protected UpdatableSqlRegistry createUpdatableSqlRegsitry() {
		return new ConcurrentHashMapSqlRegistry();
	}
}