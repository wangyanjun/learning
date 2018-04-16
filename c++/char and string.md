# char
use 1 byte to present character.

# wchar_t
use 2 bytes to present character. add prefix 'L' before literal string.
In Windows, usually use *WCHAR* typedef for *wchar_t*.
~~~c++
    wchar_t* content=L'你好！';
~~~


# std::string

1. to char*
~~~c++
    std::string s;
    char* cs=s.c_str();
~~~

# wcs wide character string

# mbs multiple bytes string