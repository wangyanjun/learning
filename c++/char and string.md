# char
use 1 byte to present character.

# wchar_t
use 2 bytes to present character. add prefix 'L' before literal string.
In Windows, usually use *WCHAR* typedef for *wchar_t*.
~~~c++
    wchar_t* content=L'你好！';
~~~

# char16_t
Unicode character
`   1
# char32_t
Unicode character

# std::string

Equal to basic_string<char>
1. to char*
~~~c++
    std::string s;
    char* cs=s.c_str();
~~~

# std::wstring
Equal to basic_string<wchar_t>

# wcs wide character string

# mbs multiple bytes string

# format