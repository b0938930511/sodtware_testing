from selenium import webdriver
from selenium.webdriver.firefox.service import Service
from webdriver_manager.firefox import GeckoDriverManager
from selenium.webdriver.support.ui import WebDriverWait

from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import re
from selenium.webdriver.firefox.options import Options


driver = webdriver.Firefox(service=Service(GeckoDriverManager().install()))
driver.get("https://www.nycu.edu.tw/")
driver.maximize_window()
WebDriverWait(driver,10).until(EC.element_to_be_clickable((By.XPATH, "//a[@href='https://www.nycu.edu.tw/news-network/']")))
driver.find_element(By.XPATH, "//a[@href='https://www.nycu.edu.tw/news-network/']").click()
driver.find_element(By.XPATH, "//a[@href='https://www.nycu.edu.tw/news/3352/']").click()
print(driver.find_element(By.XPATH, "//h1[@class='single-post-title entry-title']").text)
print(driver.find_element(By.XPATH, "//div[@class='entry-content clr']").text)



driver.switch_to.new_window('tab')
driver.get("https://www.google.com/")
keyword = driver.find_element(By.NAME, "q")
keyword.send_keys("310554034")
keyword.submit()
driver.implicitly_wait(10)

print("\n====================title=============================")
title = driver.find_elements(By.XPATH, "//h3[@class='LC20lb MBeuO DKV0Md']")
print(title[1].text)
driver.quit()