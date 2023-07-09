package cn.yusiwen.redislock.constant;

/**
 * 全局常量枚举
 *
 * @author Siwen Yu (yusiwen@gmail.com)
 */
public enum GlobalConstant {

    /**
     * Redis地址配置前缀
     */
    REDIS_CONNECTION_PREFIX("redis://", "Redis地址配置前缀");

    /**
     * Value
     */
    private final String value;
    /**
     * Description
     */
    private final String description;

    GlobalConstant(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
