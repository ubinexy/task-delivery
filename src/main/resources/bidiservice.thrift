
namespace java com.example.thrift.bidiMessageIface

struct Message {
    1: string clientName,
    2: string message
}

service MessageService {
    oneway void sendMessage(1: Message msg),
    oneway void sendGreeting(1: string name),
    oneway void messageCallback(1: string name, 2: i32 status),
}


