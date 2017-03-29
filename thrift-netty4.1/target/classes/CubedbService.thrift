namespace java com.xiaomi.cubedb.thrift

/**
struct Response {
    1:required bool success = true,
    2:optional string message,
}
**/

struct GetResult {
    1: required bool success = 0,
    2: optional string message,
    3: required binary result,
}

/**
struct MultipleGetResult {
    1: required bool success = true,
    2: optional string message,
    3: required map<string, binary> result,
}

struct CombineGetResult {
    1: required bool success = true,
    2: optional string message,
    3: required list<binary> result,
}

struct CubeDBFunc {
    1: required string className = "",
    2: optional map<string, string> context,
    3: optional map<string, string> keysInfo, // 每一个key可以带入一个detail
}
**/

exception CubeDBException {
    1: required string message,
}
service CubeDBService {
    GetResult get(1:string key) throws (1:CubeDBException ex);

    /**
    Response put(1:string key, 2:binary value);

    MultipleGetResult multipleGet(1:list<string> keys);

    Response convertPut(1:string key, 2:binary value, 3:CubeDBFunc classNameOfPut);

    GetResult convertGet(1:string key, 2:binary inputData, 3:CubeDBFunc classNameOfConvert);

    MultipleGetResult convertMultipleGet(1:list<string> keys,
                                         2:binary inputData,
                                         3:CubeDBFunc classNameOfConvert,
                                         4:optional bool isLocal);

    CombineGetResult combineMultipleGet(1:list<string> keys,
                                        2:binary inputData,
                                        3:CubeDBFunc classNameOfConvert,
                                        4:CubeDBFunc classNameOfCombine,
                                        5:bool isLocal);

    CombineGetResult combineMultipleFilter(1:list<string> includeKeys,
                                           2:list<string> excludeKeys,
                                           3:binary inputData,
                                           4:CubeDBFunc classNameOfFilter,
                                           5:CubeDBFunc classNameOfConvert,
                                           6:CubeDBFunc classNameOfMerge,
                                           7:optional bool isLocal)
                                           **/

}
