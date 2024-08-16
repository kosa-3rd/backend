provider "aws" {
  region = "ap-northeast-2"
}

resource "aws_instance" "spring_boot_server" {
  ami           = "ami-xxxxxxxxxxxxxxxxx"
  instance_type = "t2.micro"
  key_name      = "your-key-pair"

  tags = {
    Name = "SpringBoot-Server"
  }
}

resource "null_resource" "ansible_provisioner" {
  depends_on = [aws_instance.spring_boot_server]

  provisioner "local-exec" {
    command = "ansible-playbook -i '${aws_instance.spring_boot_server.public_ip},' playbook.yml"
  }
}