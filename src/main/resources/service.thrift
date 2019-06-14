namespace cpp com.example.thrift.thriftServerIface
namespace java com.example.thrift.thriftServerIface

exception InvalidOperationException {
    1: i32 code,
    2: string description
}

struct CrossPlatformResource {
    1: i32 id,
    2: string name,
    3: optional string salutation
}

service CrossPlatformService {
    CrossPlatformResource get(1:i32 id) throws (1: InvalidOperationException e),

    void save(1: CrossPlatformResource resource) throws (1: InvalidOperationException e),

    list <CrossPlatformResource> getList(),

    bool ping() throws (1: InvalidOperationException e),
}