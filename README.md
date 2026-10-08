# Kiểm thử giao diện đăng nhập Văn phòng điện tử UTC

Dự án chứa kiểm thử giao diện trang đăng nhập Văn phòng điện tử UTC bằng Selenium/pytest theo mô hình Page Object Model (POM).

## Cài dependency

```powershell
pip install -r requirements.txt
```

## Thiết lập tài khoản kiểm thử

Trong Windows PowerShell, thiết lập tên tài khoản UTC dùng cho TC02. Mật khẩu không được dùng; test gửi một mật khẩu sai giả lập.

```powershell
$env:UTC_USER="your_username"
```

Không ghi thông tin đăng nhập thật vào mã nguồn hoặc commit chúng.

## Chạy test

```powershell
pytest
```

Chrome mặc định chạy ở chế độ headless. Selenium 4 dùng Selenium Manager để tìm/quản lý driver trình duyệt.

## Test case thất bại

| ID | Dữ liệu kiểm thử | Bước chính | Kết quả mong đợi |
|---|---|---|---|
| TC01 | Tên tài khoản UTC được nối hậu tố không hợp lệ; mật khẩu sai giả lập | Mở trang đăng nhập, gửi thông tin và chờ phản hồi | Trang hiển thị `Tài khoản hoặc mật khẩu không đúng.` |

Chỉ triển khai các test case đăng nhập thất bại vì máy chủ đang có vấn đề với luồng đăng nhập thành công. Kết quả thực tế chưa được xác minh bằng cách chạy test.
